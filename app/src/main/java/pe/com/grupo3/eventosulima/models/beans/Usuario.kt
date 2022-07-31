package pe.com.grupo3.eventosulima.models.beans

data class Usuario (
    val usr_id: String,
    val username : String,
    val nombres : String,
    val password : String,
    val apellidos : String,
    val edad : Int,
    val codigoULima : Int
    )
