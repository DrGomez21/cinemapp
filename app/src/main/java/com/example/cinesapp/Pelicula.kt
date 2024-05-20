package com.example.cinesapp

import com.example.cinesapp.modelos.DescripcionPelicula
import com.example.cinesapp.modelos.DetallePelicula
import com.example.cinesapp.modelos.Horario

class Pelicula(title : String, content : String) {
    var descripcion : DescripcionPelicula = DescripcionPelicula(content)
    var detalle : DetallePelicula = DetallePelicula(content)
    var horario : List<Horario>
    val titulo = title

    init {
        horario = crearHorario(content)
    }

    private fun crearHorario(content: String): List<Horario> {

        if (content == "Sin datos") {
            return emptyList()
        }

        val regex = Regex("<strong>(.*?)</strong>:(?:Castellano: ([^Subtitulada]+))?(?:Subtitulada: ([^<]+))?")
        val matches = regex.findAll(content)

        val listaHorarios = mutableListOf<Horario>()

        for (match in matches) {
            val cine = match.groups[1]?.value
            val horariosCastellano = match.groups[2]?.value
            val horariosSubtitulada = match.groups[3]?.value

            listaHorarios.add(Horario(cine, horariosCastellano, horariosSubtitulada))
        }

        return listaHorarios
    }
}