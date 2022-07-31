package pe.com.grupo3.eventosulima

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Switch
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat

class ConfigNotifActivity : AppCompatActivity() {
    private lateinit var toolbar : Toolbar
    private lateinit var switchNotifEnable : Switch
    private lateinit var chkBoxConciertos : CheckBox
    private lateinit var chkBoxEvAcad : CheckBox
    private lateinit var chkBoxPeliculas : CheckBox
    private lateinit var chkBoxTheatre : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_notif)

        toolbar = findViewById(R.id.toolbar)
        switchNotifEnable = findViewById(R.id.switchNotifEnable)
        chkBoxConciertos = findViewById(R.id.chkBoxConciertos)
        chkBoxEvAcad = findViewById(R.id.chkBoxEvAcad)
        chkBoxPeliculas = findViewById(R.id.chkBoxPeliculas)
        chkBoxTheatre = findViewById(R.id.chkBoxTheatre)

        toolbar.title = getString(R.string.notif_title)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        switchNotifEnable
    }

    private fun crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = Constantes.NotificationData.NOTIFICATION_CHANNEL_NAME
            val descriptionText = Constantes.NotificationData.NOTIFICATION_CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(
                Constantes.NotificationData.NOTIFICATION_CHANNEL_ID,
                name,
                importance
            ).apply {
                description = descriptionText
            }

            val notificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    /*
    private fun crearNotificacion(title : String, content : String) : Notification {
        val intent = Intent(this, RegistroActivity::class.java).apply {
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(
            this,
            Constantes.NotificationData.NOTIFICATION_CHANNEL_ID
        ).setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

        return notification
    }
    */
}