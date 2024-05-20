package com.example.cinesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinesapp.databinding.ActivityMainBinding
import com.example.cinesapp.modelos.DescripcionPelicula
import com.example.cinesapp.modelos.DetallePelicula
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : PeliculaAdapter
    private val listaItems = mutableListOf<Item>()
    private val listaPeliculas = mutableListOf<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buscarCartelera()
        initRecyclerView(listaPeliculas)
    }

    private fun initRecyclerView(peliculas: MutableList<Pelicula>) {
        adapter = PeliculaAdapter(peliculas)
        val itemDecoration = GridSpacingItemDecoration(2, 16)
        binding.rvContainer.layoutManager = GridLayoutManager(this, 2)
        binding.rvContainer.adapter = adapter
        binding.rvContainer.addItemDecoration(itemDecoration)

        adapter.setOnItemClickListener(object : PeliculaAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                val gson = Gson()
                val json = gson.toJson(listaPeliculas[position])
                intent.putExtra("movie_key", json)
                startActivity(intent)
            }
        })

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.rss2json.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buscarCartelera() {
        binding.progessBar.isVisible = true
        lifecycleScope.launch(Dispatchers.IO) {
            val call = getRetrofit().create(RSSService::class.java).getCartelera()
            val movies = call.body()

            lifecycleScope.launch(Dispatchers.Main) {
                if (call.isSuccessful) {
                    val items = movies?.peliculas ?: emptyList()
                    listaItems.clear()
                    listaItems.addAll(items)

                    if (listaItems.isNotEmpty()) {
                        listaPeliculas.clear()
                        for (item in listaItems) {
                            val movie = Pelicula(item.title, item.content)
                            listaPeliculas.add(movie)
                        }
                    } else {
                        showError()
                    }
                    runOnUiThread {
                        binding.progessBar.isVisible = false
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
    }
}