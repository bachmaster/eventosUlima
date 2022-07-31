package pe.com.grupo3.eventosulima.models.beans

data class Evento (
        val titulo : String,
        val urlImagen : String,
        val director : String,
        val actores : String,
        val duracion : String,
        val genero : String,
        val diaFuncion : String,
        val horaInicio : String,
        val tipoEvento : String,
        val rating : Double,
        val capacidad : Int
        )