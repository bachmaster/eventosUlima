package pe.com.grupo3.eventosulima.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pe.com.grupo3.eventosulima.R
import pe.com.grupo3.eventosulima.models.beans.Pelicula

class ListadoPeliculasMainAdapter(private val mListaPeliculasMain : List<Pelicula>,
  private val mListener: (pelicula:Pelicula)->Unit) :
    RecyclerView.Adapter<ListadoPeliculasMainAdapter.ViewHolder>()
{
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tviPeliculaNombre2 : TextView
        val ivPelicula2 : ImageView

        init {
            tviPeliculaNombre2 = view.findViewById(R.id.tviPeliculaNombre2)
            ivPelicula2 = view.findViewById(R.id.ivPelicula2)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = mListaPeliculasMain[position]
        holder.tviPeliculaNombre2.text = pelicula.titulo

        Picasso.get()
            .load(pelicula.urlImagen)
            .resize(100, 150)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(holder.ivPelicula2)
        holder.itemView.setOnClickListener {
            mListener(pelicula)
        }
    }

    override fun getItemCount(): Int {
        return mListaPeliculasMain.size
    }
}