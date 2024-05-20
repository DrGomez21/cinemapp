package com.example.cinesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinesapp.modelos.DescripcionPelicula
import com.example.cinesapp.modelos.DetallePelicula

class PeliculaAdapter(val peliculas:List<Pelicula>): RecyclerView.Adapter<PeliculaViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val item = peliculas[position]
        holder.bind(item.descripcion.srcPoster)
    }
}