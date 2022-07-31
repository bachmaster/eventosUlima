package pe.com.grupo3.eventosulima

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import pe.com.grupo3.eventosulima.models.GestorUsuarios

class ConfigPassActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var newPassword : EditText
    private lateinit var rePassword : EditText
    private lateinit var txtMessage : TextView
    private lateinit var btnCancelar : Button
    private lateinit var btnContinuar : Button

    private var newPasswordEnabler = false
    private var passwordChangeIsAble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_pass)

        toolbar = findViewById(R.id.toolbar)
        newPassword = findViewById(R.id.newPassword)
        rePassword = findViewById(R.id.rePassword)
        txtMessage = findViewById(R.id.txtMessage)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnContinuar = findViewById(R.id.btnContinuar)

        val sp = getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE)

        toolbar.title = getString(R.string.pass_title)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        newPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (newPasswordEnabler) {
                    if (newPassword.text.toString() != rePassword.text.toString()){
                        txtMessage.visibility = View.VISIBLE
                        passwordChangeIsAble = false
                    }
                    else {
                        txtMessage.visibility = View.GONE
                        passwordChangeIsAble = true
                    }
                }
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        rePassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                newPasswordEnabler = true
                if (newPassword.text.toString() != rePassword.text.toString()){
                    txtMessage.visibility = View.VISIBLE
                    passwordChangeIsAble = false
                }
                else {
                    txtMessage.visibility = View.GONE
                    passwordChangeIsAble = true
                }
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        btnCancelar.setOnClickListener {
            finish()
        }

        btnContinuar.setOnClickListener {
            if (!passwordChangeIsAble) {
                Toast.makeText(applicationContext, "Verifique su contraseña", Toast.LENGTH_SHORT)
                    .show()
            }
            else {
                GestorUsuarios.getInstance().editarPasswordFirebase(
                    sp.getString(Constantes.USR_ID, "")!!,
                    newPassword.text.toString(),
                    {
                        Toast.makeText(applicationContext, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }
                ) {
                    Toast.makeText(applicationContext, "${it}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}