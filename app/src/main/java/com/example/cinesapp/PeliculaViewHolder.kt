package com.example.cinesapp


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesapp.databinding.ItemPeliculaBinding
import com.squareup.picasso.Picasso

class PeliculaViewHolder(view: View, listener: PeliculaAdapter.onItemClickListener): RecyclerView.ViewHolder(view) {

    private val binding = ItemPeliculaBinding.bind(view)

    fun bind(poster: String) {
        Picasso.get().load(poster).into(binding.ivPoster)
    }

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

}