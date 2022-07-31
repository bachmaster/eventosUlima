package pe.com.grupo3.eventosulima.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pe.com.grupo3.eventosulima.R
import pe.com.grupo3.eventosulima.models.beans.Evento

class ListadoEventosMainAdapter(private val mListaEventosMain: List<Evento>,
    private val mListener: (evento: Evento) -> Unit):
    RecyclerView.Adapter<ListadoEventosMainAdapter.ViewHolder>()
{
        class ViewHolder (view : View) : RecyclerView.ViewHolder(view) {
            val tviEventoNombre2 : TextView
            val ivEvento2 : ImageView

            init {
                tviEventoNombre2 = view.findViewById(R.id.tviPeliculaNombre2)
                ivEvento2 = view.findViewById(R.id.ivPelicula2)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evento2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evento = mListaEventosMain[position]
        holder.tviEventoNombre2.text = evento.titulo

        Picasso.get()
            .load(evento.urlImagen)
            .resize(100, 150)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(holder.ivEvento2)

        holder.itemView.setOnClickListener {
            mListener(evento)
        }
    }

    override fun getItemCount(): Int {
        return mListaEventosMain.size
    }

}