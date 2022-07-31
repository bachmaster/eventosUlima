package pe.com.grupo3.eventosulima.networking

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkingManager (val service : PeliculaService) {
    companion object {
        private var instance : NetworkingManager? = null


        fun getInstance() : NetworkingManager {
            if(instance == null) {
                /*val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()*/

                val retrofit : Retrofit = Retrofit.Builder()
                    .baseUrl("http://demo5967447.mockable.io/dataPeliculas/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(PeliculaService::class.java)


                instance = NetworkingManager(service)
            }
            return instance!!
        }
    }
}