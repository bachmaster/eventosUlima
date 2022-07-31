package pe.com.grupo3.eventosulima.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pe.com.grupo3.eventosulima.R
import pe.com.grupo3.eventosulima.models.beans.Evento

class ListadoEventosAdapter(private val mListaEventos : List<Evento>,
                            private val mOnItemClickListener: (evento: Evento)->Unit)
    : RecyclerView.Adapter<ListadoEventosAdapter.ViewHolder>(){
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tviEventoNombre : TextView
        val ivEvento : ImageView
        val tviDiaEvento : TextView
        val tviHorarioEvento : TextView
        val tviRatingEvento : TextView
        val butCalificarEvento : Button

        init {
            tviEventoNombre = view.findViewById(R.id.tviEventoNombre)
            ivEvento = view.findViewById(R.id.ivEvento)
            tviDiaEvento = view.findViewById(R.id.tviDiaEvento)
            tviHorarioEvento = view.findViewById(R.id.tviHorarioEvento)
            tviRatingEvento = view.findViewById(R.id.tviRatingEvento)
            butCalificarEvento = view.findViewById(R.id.butCalificarEvento)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evento, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evento = mListaEventos[position]
        holder.tviEventoNombre.text = evento.titulo
        holder.tviDiaEvento.text = evento.diaFuncion
        holder.tviHorarioEvento.text = evento.horaInicio
        holder.tviRatingEvento.text = "" + evento.rating

        Picasso.get()
            .load(evento.urlImagen)
            .resize(100, 150)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(holder.ivEvento)

        holder.itemView.setOnClickListener {
            mOnItemClickListener(evento)
        }

    }

    override fun getItemCount(): Int {
        return mListaEventos.size
    }
}