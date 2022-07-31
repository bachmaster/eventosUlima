package pe.com.grupo3.eventosulima

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
//import pe.com.grupo3.eventosulima.databinding.ActivityButacasBinding
//import pe.com.grupo3.eventosulima.databinding.ActivityMainBinding

class ButacasActivity: AppCompatActivity(), AdapterView.OnItemClickListener {
    /*private lateinit var binding: ActivityButacasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButacasBinding.inflate(layoutInflater)
        setContentView( binding.root)

        val cant_butacas = resources.getStringArray(R.array.cant_butacas)
        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_item,
            cant_butacas
        )

        with(binding.autoCompleteTextView) {
            setAdapter(adapter)
            onItemClickListener=this@ButacasActivity
        }
    }*/

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item=parent?.getItemAtPosition(position) as String
        Toast.makeText(this, "Seleccionado: $item", Toast.LENGTH_SHORT).show()
    }
}