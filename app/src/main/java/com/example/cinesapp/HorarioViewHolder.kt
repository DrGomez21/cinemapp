package com.example.cinesapp

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesapp.databinding.ItemHorarioBinding

class HorarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHorarioBinding.bind(view)

    fun bind(title: String?, horarioCastellano: String?, horarioSubtitulado: String?) {
        binding.tvNombreCine.text = title ?: "Cine no encontrado"
        binding.bExpandir.setOnClickListener { expandirCard() }
        binding.tvCastellano.text = horarioCastellano ?: "No hay datos"
        binding.tvSubtitulado.text = horarioSubtitulado ?: "No hay datos"
    }

    private fun expandirCard() {
        if (binding.llHorariosCastellano.visibility == View.GONE &&
            binding.llHorariosSubtitulados.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(binding.cardViewMain, AutoTransition())
            binding.llHorariosCastellano.visibility = View.VISIBLE
            binding.llHorariosSubtitulados.visibility = View.VISIBLE
        } else {
            TransitionManager.beginDelayedTransition(binding.cardViewMain, AutoTransition())
            binding.llHorariosCastellano.visibility = View.GONE
            binding.llHorariosSubtitulados.visibility = View.GONE
        }
    }
}