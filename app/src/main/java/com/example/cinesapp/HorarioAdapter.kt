package com.example.cinesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesapp.modelos.Horario

class HorarioAdapter(val horarios:List<Horario>): RecyclerView.Adapter<HorarioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_horario, parent, false)
        return HorarioViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return horarios.size
    }

    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
        val item = horarios[position]
        holder.bind(item.cine, item.horarioCastellano, item.horarioSubtitulado)
    }
}