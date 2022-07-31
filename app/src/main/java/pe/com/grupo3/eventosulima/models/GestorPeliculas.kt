package pe.com.grupo3.eventosulima.models

import android.os.Handler
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.com.grupo3.eventosulima.models.beans.Pelicula
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class GestorPeliculas {
    val dbFirebase = Firebase.firestore

    val handler : Handler = Handler()

    fun obtenerListaPeliculasFirebase(success : (List<Pelicula>) -> Unit, error : (String) -> Unit) {
        val peliculasCol = dbFirebase.collection("Peliculas")

        peliculasCol.get()
            .addOnSuccessListener {
                val listaPeliculas = it.documents.map { documentSnapshot ->
                    Pelicula(
                        documentSnapshot["titulo"].toString(),
                        documentSnapshot["urlImagen"].toString(),
                        documentSnapshot["director"].toString(),
                        documentSnapshot["actores"].toString(),
                        documentSnapshot["fecha"].toString(),
                        documentSnapshot["idioma"].toString(),
                        documentSnapshot["pais"].toString(),
                        documentSnapshot["duracion"].toString(),
                        documentSnapshot["genero"].toString(),
                        documentSnapshot["diaFuncion"].toString(),
                        documentSnapshot["horaInicio"].toString(),
                        documentSnapshot["rating"].toString().toDouble(),
                        Integer.parseInt(documentSnapshot["capacidad"].toString())
                    )
                }
                success(listaPeliculas)
            }.addOnFailureListener {
                error(it.message.toString())
            }
    }


    fun guardarListaPeliculasFirebase(peliculas : List<Pelicula>, success : () -> Unit, error: (String) -> Unit) {
        val peliculasCol = dbFirebase.collection("Peliculas")
        Log.i(null, peliculasCol.document().get().toString())

            dbFirebase.runTransaction { transaction ->
                peliculas.forEach {
                    val mapPelicula = hashMapOf(
                        "titulo" to it.titulo,
                        "urlImagen" to it.urlImagen,
                        "director" to it.director,
                        "actores" to it.actores,
                        "fecha" to it.fecha,
                        "idioma" to it.idioma,
                        "pais" to it.pais,
                        "duracion" to it.duracion,
                        "genero" to it.genero,
                        "diaFuncion" to it.diaFuncion,
                        "horaInicio" to it.horaInicio,
                        "rating" to it.rating,
                        "capacidad" to it.capacidad
                    )
                    transaction.set(peliculasCol.document(), mapPelicula)
                }
            }.addOnSuccessListener {
                success()
            }.addOnFailureListener {
                error(it.message.toString())
            }
        }

    fun obtenerListaPeliculasCorrutinas(): List<Pelicula> {
        var resultado = mutableListOf<Pelicula>()
        val url1 = generateUrl("https://drive.google.com/file/d/1RXbfsFuGdn3vlNvjKy9qNU08bDe-5Ycl/view?usp=sharing")
        val url = URL(url1)
        val urlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.doInput = true
        urlConnection.readTimeout = 300000
        urlConnection.connectTimeout = 20000




        try {
            urlConnection.connect()
            val response = urlConnection.responseCode

            if(response == HttpURLConnection.HTTP_OK) {
                val ingreso : BufferedReader = urlConnection.inputStream.bufferedReader()
                var inputLine : String?
                var cont = 0
                ingreso.readLine()

                while((ingreso.readLine().also { inputLine = it }) != null) {
                    var ter : MutableList<String> = inputLine!!.split(";") as MutableList<String>


                    if(ter[12] != "25"){
                        ter[12] = "25"
                    }

                    Log.i(null, ter[0].toString())
                    var pelicula = Pelicula(ter[0],ter[1],ter[2],ter[3].toString(),ter[4],ter[5],ter[6],ter[7],ter[8].toString(),
                    ter[9],ter[10],ter[11].toDouble(),Integer.parseInt(ter[12]))

                    Log.i("",pelicula.toString())
                    resultado.add(pelicula)
                    cont++
                }
                ingreso.close()
            }
        } finally {
            urlConnection.disconnect()
        }
        return resultado
    }

   fun generateUrl(s: String): String? {
        val p = s.split("/").toTypedArray()
        return "https://drive.google.com/uc?export=download&id=" + p[5]
    }

}