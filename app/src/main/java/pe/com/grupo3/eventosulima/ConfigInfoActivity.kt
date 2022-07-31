package pe.com.grupo3.eventosulima

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import pe.com.grupo3.eventosulima.models.GestorUsuarios
import pe.com.grupo3.eventosulima.models.beans.Usuario

class ConfigInfoActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var editTextLastName : EditText
    private lateinit var editTextFirstName : EditText
    private lateinit var editTextDate : EditText
    private lateinit var editTextCodAlumno : EditText
    private lateinit var btnCancelar : Button
    private lateinit var btnContinuar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_info)

        toolbar = findViewById(R.id.toolbar)
        editTextLastName = findViewById(R.id.newPassword)
        editTextFirstName = findViewById(R.id.rePassword)
        editTextDate = findViewById(R.id.editTextDate)
        editTextCodAlumno = findViewById(R.id.editTextCodAlumno)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnContinuar = findViewById(R.id.btnContinuar)

        val sp = getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE)
        val editor = sp.edit()

        toolbar.title = getString(R.string.edit_title)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        editTextLastName.setText(sp.getString(Constantes.APELLIDOS, ""))
        editTextFirstName.setText(sp.getString(Constantes.NOMBRES, ""))
        editTextDate.setText(sp.getString(Constantes.EDAD, "0"))
        editTextCodAlumno.setText(sp.getString(Constantes.CODIGO_ULIMA, "0"))

        btnCancelar.setOnClickListener {
            finish()
        }

        btnContinuar.setOnClickListener{
            val usuario = Usuario(
                sp.getString(Constantes.USR_ID, "")!!,
                sp.getString(Constantes.USERNAME, "")!!,
                editTextFirstName.text.toString(),
                "",
                editTextLastName.text.toString(),
                Integer.parseInt(editTextDate.text.toString()),
                Integer.parseInt(sp.getString(Constantes.CODIGO_ULIMA, "")!!)
            )

            editor.putString(Constantes.NOMBRES, editTextFirstName.text.toString())
            editor.putString(Constantes.APELLIDOS, editTextLastName.text.toString())
            editor.putString(Constantes.EDAD, editTextDate.text.toString())

            GestorUsuarios.getInstance()
                .editarUsuarioFirebase(
                    usuario,
                    {
                        Toast.makeText(applicationContext,"Datos Actualizados Correctamente", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }
                ){
                    Toast.makeText(applicationContext, "${it}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}