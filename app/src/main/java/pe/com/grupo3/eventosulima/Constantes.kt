package pe.com.grupo3.eventosulima

import pe.com.grupo3.eventosulima.models.beans.Usuario

class Constantes {
    companion object {
        val NOMBRE_SP = "SP_EVENTOSAPP"
        val SP_ESTA_SINCRONIZADO = "ESTA_SINCRONIZADO"
        val SP_ESTA_LOGEADO = "ESTA_LOGEADO"
        val SP_ESTA_SINCRONIZADO_EVENTOS = "ESTA_SINCRONIZADO_EVENTO"
        val USR_ID = "UsrId"
        val USERNAME = "Username"
        val APELLIDOS = "Apellidos"
        val NOMBRES = "Nombres"
        val CODIGO_ULIMA = "CodigoUlima"
        val EDAD = "Edad"
        val RUTA_FOTO = "rutaFoto"

    }
    class NotificationData {
        companion object {
            val NOTIFICATION_CHANNEL_ID = "1"
            val NOTIFICATION_CHANNEL_NAME = "LOGIN"
            val NOTIFICATION_CHANNEL_DESCRIPTION = "Notification channel for login purposes"
        }
    }
}