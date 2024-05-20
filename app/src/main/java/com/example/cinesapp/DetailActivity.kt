package com.example.cinesapp

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinesapp.databinding.ActivityDetailBinding
import com.example.cinesapp.modelos.Horario
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter : HorarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val json = intent.getStringExtra("movie_key")
        val gson = Gson()
        val pelicula = gson.fromJson(json, Pelicula::class.java)

        binding.tvTitulo.text = pelicula.titulo
        binding.tvSinopsis.text = pelicula.descripcion.sinopsis
        binding.tvGenero.text = pelicula.detalle.generos
        binding.tvDuracion.text = pelicula.detalle.duracion
        binding.tvCalificacion.text = pelicula.detalle.calificacion

        Picasso.get().load(pelicula.descripcion.srcPoster).into(binding.ivDetallePoster)
        val colorFilter = PorterDuffColorFilter(Color.argb(128, 0, 0, 0), PorterDuff.Mode.DARKEN)
        binding.ivDetallePoster.colorFilter = colorFilter

        binding.btnTrailer.setOnClickListener { abrirYoutube(pelicula.descripcion.trailer) }

        initRecyclerView(pelicula.horario)
    }

    private fun initRecyclerView(listaHorarios: List<Horario>) {
        adapter = HorarioAdapter(listaHorarios)
        binding.rvHorarios.layoutManager = LinearLayoutManager(this)
        binding.rvHorarios.adapter = adapter
    }

    private fun abrirYoutube(url: String?) {
        if (url.isNullOrEmpty()) {
            Toast.makeText(this,
                "No se pudo abrir el enlace al video",
                Toast.LENGTH_SHORT).show()
        } else {
            val uri = Uri.parse(url)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

}