package pe.com.grupo3.eventosulima

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pe.com.grupo3.eventosulima.models.GestorUsuarios
import pe.com.grupo3.eventosulima.models.beans.Usuario

class RegisterActivity: AppCompatActivity() {

    private lateinit var mEteUsername : EditText
    private lateinit var mEtePassword : EditText
    private lateinit var mEteNombres : EditText
    private lateinit var mEteApellidos : EditText
    private lateinit var mEteEdad : EditText
    private lateinit var  mEteCodigo : EditText
    private lateinit var  mEteRepPassword : EditText
    private lateinit var  mButRegresar : Button
    private lateinit var  mButRegistrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mEteUsername = findViewById(R.id.eteUsername)
        mEtePassword = findViewById(R.id.etePassword)
        mEteNombres = findViewById(R.id.eteNombres)
        mEteApellidos = findViewById(R.id.eteApellidos)
        mEteEdad = findViewById(R.id.eteEdad)
        mEteCodigo = findViewById(R.id.eteCodigoULima)
        mEteRepPassword = findViewById(R.id.eteRepContraseña)
        mButRegresar = findViewById(R.id.butRegresarRegistro)
        mButRegistrar = findViewById(R.id.butConfirmarRegistro)


        mButRegistrar.setOnClickListener {
            if(mEtePassword.text.toString() == mEteRepPassword.text.toString()){
                GlobalScope.launch(Dispatchers.Main) {
                    val gestor = GestorUsuarios.getInstance()
                    val usuario = Usuario(
                        "",
                        mEteUsername.text.toString(),
                        mEteNombres.text.toString(), mEtePassword.text.toString(),
                        mEteApellidos.text.toString(),
                        Integer.parseInt(mEteEdad.text.toString()),
                        Integer.parseInt(mEteCodigo.text.toString()))
                    gestor.guardarUsuarioFirebase(
                        usuario,
                        {
                            Toast.makeText(applicationContext,"Usuario Registrado Correctamente",Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                        }
                    ){
                        Toast.makeText(applicationContext, "${it}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Contraseñas no compatibles", Toast.LENGTH_SHORT)
            }
        }

        mButRegresar.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}