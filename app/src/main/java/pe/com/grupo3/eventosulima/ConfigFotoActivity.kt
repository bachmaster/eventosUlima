package pe.com.grupo3.eventosulima

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ConfigFotoActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var mConstraintLayoutFoto: ConstraintLayout
    private var mFotoPath : String? = null
    private lateinit var iviFoto : ImageView
    private lateinit var butCancelarFoto : Button
    private lateinit var  butContinuarFoto : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_foto)

        toolbar = findViewById(R.id.toolbar)
        iviFoto = findViewById(R.id.iviFotoConfig)

        toolbar.title = getString(R.string.foto_title)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        mConstraintLayoutFoto = findViewById(R.id.constraintLayoutFoto)

        butContinuarFoto = findViewById(R.id.butContinuarFoto)
        butCancelarFoto = findViewById(R.id.butCancelarFoto)

        mConstraintLayoutFoto.setOnClickListener {
            tomarFoto()
        }

        butContinuarFoto.setOnClickListener {
            val intent = Intent(applicationContext, ConfigActivity::class.java)
            startActivity(intent)
        }

        butCancelarFoto.setOnClickListener {
            val intent = Intent(applicationContext, ConfigActivity::class.java)
            startActivity(intent)
        }
    }

    private fun tomarFoto() {
        val imageFile = crearArchivoImagen()
        if(imageFile != null){
            val fotoUri = FileProvider.getUriForFile(
                this,
                "pe.com.grupo3.eventosulima.fileprovider",
                imageFile
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == RESULT_OK) {
            mostrarFoto()
        }
    }

    override fun onResume() {
        super.onResume()
        val sp = getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE)

        mFotoPath = sp.getString(Constantes.RUTA_FOTO,"")

        if(mFotoPath == ""){

        }else{
            mostrarFoto()
        }
    }

    private fun mostrarFoto() {
        val matriz = Matrix()
        val angulo = obtenerAnguloRotacion()
        matriz.postRotate(angulo)

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(mFotoPath!!, options)

        // Cálculo de espacio disponible
        val iviHeight = iviFoto.height
        val iviWidth = iviFoto.width

        // Calcular el factor de escalamiento
        var scaleFactor = 1
        if(angulo == 90f || angulo == 270f){
            scaleFactor = Math.min(
                iviWidth / options.outHeight,
                iviHeight / options.outWidth
            )
        }
        else{
            scaleFactor = Math.min(
                iviWidth / options.outWidth,
                iviHeight / options.outHeight
            )
        }

        options.inJustDecodeBounds = false
        options.inSampleSize = scaleFactor

        val bitmap : Bitmap = BitmapFactory.decodeFile(mFotoPath!!, options)
        val bitmapRotated = Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matriz,
            true)
        iviFoto.setImageBitmap(bitmapRotated)
    }

    private fun obtenerAnguloRotacion(): Float {
        val exifInterface = ExifInterface(mFotoPath!!)
        val orientation = exifInterface.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )

        if(orientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90f
        }
        else if (orientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180f
        }
        else if(orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270f
        }
        else{
            return 0f
        }
    }

    private fun crearArchivoImagen(): File? {
        val sp = getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE)
        val rutaFoto = sp.getString(Constantes.RUTA_FOTO, "")
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val nombreArchivo = "EVENTOSUL_${timeStamp}"
        val folderAlmacenamiento = getExternalFilesDir(
            Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile(
            nombreArchivo,
            ".jpg",
            folderAlmacenamiento)
        mFotoPath = imageFile.absolutePath
        sp.edit().putString(
            Constantes.RUTA_FOTO, mFotoPath
        ).commit()



        return imageFile
    }
}