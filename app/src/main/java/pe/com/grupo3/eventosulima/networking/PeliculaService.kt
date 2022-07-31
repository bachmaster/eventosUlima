package pe.com.grupo3.eventosulima.networking

import pe.com.grupo3.eventosulima.networking.beans.PeliculasResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PeliculaService {
    @GET
    fun obtenerPeliculas() : Call<PeliculasResponse>
}