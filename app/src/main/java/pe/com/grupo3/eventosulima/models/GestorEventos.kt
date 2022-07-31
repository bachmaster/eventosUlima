package pe.com.grupo3.eventosulima.models

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.os.Handler
import android.util.Log
import pe.com.grupo3.eventosulima.models.beans.Evento
import pe.com.grupo3.eventosulima.models.beans.Pelicula
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class GestorEventos {
    val dbFirebase = Firebase.firestore

    val handler : Handler = Handler()

    fun obtenerListaEventosFirebase(success : (List<Evento>) -> Unit, error : (String) -> Unit) {
        val eventosCol = dbFirebase.collection("Eventos")

        eventosCol.get()
            .addOnSuccessListener {
                val listaEventos = it.documents.map { documentSnapshot ->
                    Evento(
                        documentSnapshot["titulo"].toString(),
                        documentSnapshot["urlImagen"].toString(),
                        documentSnapshot["director"].toString(),
                        documentSnapshot["actores"].toString(),
                        documentSnapshot["duracion"].toString(),
                        documentSnapshot["genero"].toString(),
                        documentSnapshot["diaFuncion"].toString(),
                        documentSnapshot["horaInicio"].toString(),
                        documentSnapshot["tipoEvento"].toString(),
                        documentSnapshot["rating"].toString().toDouble(),
                        Integer.parseInt(documentSnapshot["capacidad"].toString())
                    )
                }
                success(listaEventos)
            }.addOnFailureListener {
                error(it.message.toString())
            }
    }

    fun guardarListaEventosFirebase(eventos : List<Evento>, success : () -> Unit, error: (String) -> Unit) {
        val eventosCol = dbFirebase.collection("Eventos")

        dbFirebase.runTransaction { transaction ->
            eventos.forEach {
                val mapEvento = hashMapOf(
                    "titulo" to it.titulo,
                    "urlImagen" to it.urlImagen,
                    "director" to it.director,
                    "actores" to it.actores,
                    "duracion" to it.duracion,
                    "genero" to it.genero,
                    "diaFuncion" to it.diaFuncion,
                    "horaInicio" to it.horaInicio,
                    "tipoEvento" to it.tipoEvento,
                    "rating" to it.rating,
                    "capacidad" to it.capacidad
                )
                transaction.set(eventosCol.document(), mapEvento)

            }
        }.addOnSuccessListener {
            success()
        }.addOnFailureListener {
            error(it.message.toString())
        }
    }

    fun obtenerListaEventosCorrutinas(): List<Evento> {
        var resultado = mutableListOf<Evento>()
        val url1 = generateUrl("https://drive.google.com/file/d/18lwfOzfoJqdJSOIUKeFfvhDNbBvjqimp/view?usp=sharing")
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
                    var ter : MutableList<String> = inputLine!!.split(",") as MutableList<String>




                    Log.i(null, ter[0].toString())
                    var evento = Evento(ter[0],ter[1],ter[2],ter[3].toString(),ter[4],ter[5],ter[6],ter[7],ter[8].toString(), ter[9].toDouble(),Integer.parseInt(ter[10]))

                    Log.i("",evento.toString())
                    resultado.add(evento)
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