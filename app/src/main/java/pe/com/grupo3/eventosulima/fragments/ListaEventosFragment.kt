package pe.com.grupo3.eventosulima.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.grupo3.eventosulima.Constantes
import pe.com.grupo3.eventosulima.R
import pe.com.grupo3.eventosulima.adapters.ListadoEventosAdapter
import pe.com.grupo3.eventosulima.models.GestorEventos
import pe.com.grupo3.eventosulima.models.beans.Evento


class ListaEventosFragment : Fragment() {

    private lateinit var mrviEventos : RecyclerView

    private val TAG = "MainActivity"

    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "LISTA DE EVENTOS"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista_eventos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mrviEventos = view.findViewById(R.id.rviEventos)
        val gestor = GestorEventos()
        val sp = requireActivity().getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE)
        GlobalScope.launch(Dispatchers.Main) {
            val estaSincronizadoEvento = sp.getBoolean(Constantes.SP_ESTA_SINCRONIZADO_EVENTOS, false)
            var lista : List<Evento> = mutableListOf()
            if(!estaSincronizadoEvento) {
                lista = withContext(Dispatchers.IO) {
                    gestor.obtenerListaEventosCorrutinas()
                }
                gestor.guardarListaEventosFirebase(lista, {
                    sp.edit().putBoolean(
                        Constantes.SP_ESTA_SINCRONIZADO_EVENTOS, true).commit()
                    cargarListaEventos(lista)

                }){
                    Toast.makeText(requireActivity(),
                        "Error: ${it}", Toast.LENGTH_SHORT).show()
                }
            }else {
                Log.i(null, "Se ingresa aquí")
                gestor.obtenerListaEventosFirebase({
                    cargarListaEventos(it)
                }){
                    Toast.makeText(requireActivity(),
                        "Error: ${it}", Toast.LENGTH_SHORT).show()
                }
            }
        }


        mAdView = view.findViewById(R.id.adView)
        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView?.loadAd(adRequest)

    }

    private fun cargarListaEventos(lista: List<Evento>) {
        val adapter = ListadoEventosAdapter(lista){
            val argumentos = Bundle()

            argumentos.putString("tituloEvento", it.titulo)
            argumentos.putString("urlImagenEvento", it.urlImagen)
            argumentos.putString("directorEvento", it.director)
            argumentos.putString("actoresEvento", it.actores)
            argumentos.putString("duracionEvento", it.duracion)
            argumentos.putString("generoEvento", it.genero)
            argumentos.putString("diaFuncionEvento", it.diaFuncion)
            argumentos.putString("horaInicioEvento", it.horaInicio)
            argumentos.putString("tipoEvento", it.tipoEvento)

            val eventoFragment = EventoFragment()
            eventoFragment.arguments = argumentos

            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.fcvEleccion, eventoFragment)
            ft.addToBackStack(null)
            ft.commit()
        }
        mrviEventos.adapter = adapter

    }
}