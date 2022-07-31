package pe.com.grupo3.eventosulima.fragments

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import pe.com.grupo3.eventosulima.R

class EventoFragment : Fragment() {
    private var tituloEvento : String? = null
    private var urlImagenEvento : String? = null
    private var directorEvento : String? = null
    private var actoresEvento : String? = null
    private var duracionEvento : String? = null
    private var generoEvento : String? = null
    private var diaFuncionEvento : String? = null
    private var horaInicioEvento : String? = null
    private var tipoEvento : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tituloEvento = it.getString("tituloEvento")
            urlImagenEvento = it.getString("urlImagenEvento")
            directorEvento = it.getString("directorEvento")
            actoresEvento = it.getString("actoresEvento")
            duracionEvento = it.getString("duracionEvento")
            generoEvento = it.getString("generoEvento")
            diaFuncionEvento = it.getString("diaFuncionEvento")
            horaInicioEvento = it.getString("horaInicioEvento")
            tipoEvento = it.getString("tipoEvento")
        }
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        val v= inflater.inflate(R.layout.fragment_evento, container, false)
        val txtTituloEvento = v.findViewById<TextView>(R.id.tviEvento)
        val ivImagenEvento = v.findViewById<ImageView>(R.id.imagenEvento)
        val txtDirectorEvento = v.findViewById<TextView>(R.id.directorEvento)
        val txtActoresEvento = v.findViewById<TextView>(R.id.tviActoresEvento)
        val txtPuracionEvento = v.findViewById<TextView>(R.id.tviDuracionEvento)
        val txtGeneroEvento = v.findViewById<TextView>(R.id.tviGeneroEvento)
        val txtDiaFuncionEvento = v.findViewById<TextView>(R.id.tviDiaFuncionEvento)
        val txtHoraInicioEvento = v.findViewById<TextView>(R.id.tviHorarioFuncionEvento)
        val txttipoEvento = v.findViewById<TextView>(R.id.tviTipoEvento)
        txtTituloEvento.text = tituloEvento
        Picasso.get()
            .load(urlImagenEvento)
            .resize(250, 250)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(ivImagenEvento)
        txtDirectorEvento.text = directorEvento
        txtActoresEvento.text = actoresEvento
        txtPuracionEvento.text = duracionEvento
        txtGeneroEvento.text = generoEvento
        txtDiaFuncionEvento.text = diaFuncionEvento
        txtHoraInicioEvento.text = horaInicioEvento
        txttipoEvento.text = tipoEvento

        Log.i(null, txtHoraInicioEvento.text.toString())

        //boton retroceder
        val btn = v.findViewById<ImageButton>(R.id.butRegresarEvento)
        btn?.setOnClickListener {
            val ft = requireActivity().supportFragmentManager
            ft.popBackStack()
        }


        val btn1 = v.findViewById<Button>(R.id.btnReservarEvento)

        btn1.isEnabled = txttipoEvento.text != "PAGADO"

        return v

    }
}