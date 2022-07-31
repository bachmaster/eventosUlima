package pe.com.grupo3.eventosulima.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pe.com.grupo3.eventosulima.R
import pe.com.grupo3.eventosulima.fragments.CalificacionPeliculaFragment
import pe.com.grupo3.eventosulima.fragments.PeliculaFragment
import pe.com.grupo3.eventosulima.models.beans.Pelicula

class ListadoPeliculasAdapter(private val mListaPeliculas : List<Pelicula>,
     private val mOnItemClickListener: (pelicula:Pelicula)->Unit) :
    RecyclerView.Adapter<ListadoPeliculasAdapter.ViewHolder>()
{
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tviPeliculaNombre : TextView
        val tviPelicula : ImageView
        val tviDiaFuncion : TextView
        val tviHorario : TextView
        val tviRating : TextView

        init {
            tviPeliculaNombre = view.findViewById(R.id.tviPeliculaNombre)
            tviPelicula = view.findViewById(R.id.ivPelicula)
            tviDiaFuncion = view.findViewById(R.id.tviDia)
            tviHorario = view.findViewById(R.id.tviHorario)
            tviRating = view.findViewById(R.id.tviRating)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula, parent, false)

        //capturar el boton butCalificar
        val butCalificar = view.findViewById<Button>(R.id.butCalificar)
        butCalificar.setOnClickListener {

        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = mListaPeliculas[position]
        holder.tviPeliculaNombre.text = pelicula.titulo
        holder.tviDiaFuncion.text = pelicula.diaFuncion
        holder.tviHorario.text = pelicula.horaInicio
        holder.tviRating.text = "" + pelicula.rating

        Picasso.get()
            .load(pelicula.urlImagen)
            .resize(250, 250)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(holder.tviPelicula)

        holder.itemView.setOnClickListener {
            mOnItemClickListener(pelicula)
        }

    }

    override fun getItemCount(): Int {
        return mListaPeliculas.size
    }
}