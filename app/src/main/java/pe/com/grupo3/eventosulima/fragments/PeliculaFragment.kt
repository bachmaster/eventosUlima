package pe.com.grupo3.eventosulima.fragments

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import pe.com.grupo3.eventosulima.R

class PeliculaFragment: Fragment() {
    private var tituloPelicula : String? = null
    private var urlImagenPelicula : String? = null
    private var directorPelicula : String? = null
    private var actoresPelicula : String? = null
    private var fechaPelicula : String? = null
    private var idiomaPelicula : String? = null
    private var paisPelicula : String? = null
    private var duracionPelicula : String? = null
    private var generoPelicula : String? = null
    private var diaFuncionPelicula : String? = null
    private var horaInicioPelicula : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tituloPelicula = it.getString("tituloPelicula")
            urlImagenPelicula = it.getString("urlImagenPelicula")
            directorPelicula = it.getString("directorPelicula")
            actoresPelicula = it.getString("actoresPelicula")
            fechaPelicula = it.getString("fechaPelicula")
            idiomaPelicula = it.getString("idiomaPelicula")
            paisPelicula = it.getString("paisPelicula")
            duracionPelicula = it.getString("duracionPelicula")
            generoPelicula = it.getString("generoPelicula")
            diaFuncionPelicula = it.getString("diaFuncionPelicula")
            horaInicioPelicula = it.getString("horaInicioPelicula")
        }
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        val v= inflater.inflate(R.layout.fragment_pelicula, container, false)
        val txtTituloPelicula = v.findViewById<TextView>(R.id.tituloPelicula)
        val ivImagenPelicula = v.findViewById<ImageView>(R.id.ivImagenPelicula)
        val txtDirectorPelicula = v.findViewById<TextView>(R.id.directorPelicula)
        val txtActoresPelicula = v.findViewById<TextView>(R.id.actoresPelicula)
        val txtFechaPelicula = v.findViewById<TextView>(R.id.fechaPelicula)
        val txtIdiomaPelicula = v.findViewById<TextView>(R.id.idiomaPelicula)
        val txtPaisPelicula = v.findViewById<TextView>(R.id.paisPelicula)
        val txtPuracionPelicula = v.findViewById<TextView>(R.id.duracionPelicula)
        val txtGeneroPelicula = v.findViewById<TextView>(R.id.generoPelicula)
        val txtDiaFuncionPelicula = v.findViewById<TextView>(R.id.diaFuncionPelicula)
        val txtHoraInicioPelicula = v.findViewById<TextView>(R.id.horaInicioPelicula)
        txtTituloPelicula.text = tituloPelicula
        Picasso.get()
            .load(urlImagenPelicula)
            .resize(250, 250)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(ivImagenPelicula)
        txtDirectorPelicula.text = directorPelicula
        txtActoresPelicula.text = actoresPelicula
        txtFechaPelicula.text = fechaPelicula
        txtIdiomaPelicula.text = idiomaPelicula
        txtPaisPelicula.text = paisPelicula
        txtPuracionPelicula.text = duracionPelicula
        txtGeneroPelicula.text = generoPelicula
        txtDiaFuncionPelicula.text = diaFuncionPelicula
        txtHoraInicioPelicula.text = horaInicioPelicula

        //boton retroceder
        val btn = v.findViewById<ImageButton>(R.id.butRegresar)
        btn?.setOnClickListener {
            val ft = requireActivity().supportFragmentManager
            ft.popBackStack()
        }
        return v
    }
}
