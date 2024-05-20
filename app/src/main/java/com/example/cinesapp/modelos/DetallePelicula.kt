package com.example.cinesapp.modelos

class DetallePelicula(content: String) {
    val director: String
    val actores: String
    val generos: String
    val origen: String
    val duracion: String
    val calificacion: String
    val distribuidora: String

    init {
        generos = setGeneros(content)
        director = setDirector(content)
        origen = setOrigen(content)
        actores = setActores(content)
        duracion = setDuracion(content)
        calificacion = setCalificacion(content)
        distribuidora = setDistribuidora(content)
    }

    private fun setDuracion(content: String): String {
        val patron = Regex("DURACI[OÓoó]N: ([^<]+)")
        val coincidencias = patron.find(content)
        return if (coincidencias != null) {
            coincidencias.groups[1]?.value ?:"No encontrados"
        } else {
            "No encontrados"
        }
    }

    private fun setCalificacion(content: String): String {
        val patron = Regex("CALIFICACI[OÓoó]N: ([^<]+)")
        val coincidencias = patron.find(content)
        return if (coincidencias != null) {
            coincidencias.groups[1]?.value ?:"No encontrados"
        } else {
            "No encontrados"
        }
    }

    private fun setDistribuidora(content: String) : String {
        val patron = Regex("DISTRIBUIDORA: (.*?)(?:<br>|<)")
        val coincidencias = patron.find(content)
        return if (coincidencias != null) {
            coincidencias.groups[1]?.value ?:"No encontrados"
        } else {
            "No encontrados"
        }
    }

    private fun setActores(content: String): String {
        val patron = Regex("ACTORES: (.*?)(?:<br>|<)")
        val coincidencias = patron.find(content)
        return if (coincidencias != null) {
            coincidencias.groups[1]?.value ?:"No encontrados"
        } else {
            "No encontrados"
        }
    }

    private fun setOrigen(content: String): String {
        val patron = Regex("ORIGEN: (.*?)(?:<br>|<)")
        val coincidencias = patron.find(content)
        return if (coincidencias != null) {
            coincidencias.groups[1]?.value ?:"No encontrados"
        } else {
            "No encontrados"
        }
    }

    private fun setDirector(content: String): String {
        val patron = Regex("DIRECCI[ÓOoó]N: (.*?)(?:<br>|<)")
        val coincidencias = patron.find(content)
        return if (coincidencias != null) {
            coincidencias.groups[1]?.value ?:"No encontrados"
        } else {
            "No encontrados"
        }
    }

    private fun setGeneros(content: String): String {
        val patron = Regex("G[ÉEeé]NERO: ([^<]+)")
        val coincidencias = patron.find(content)
        return if (coincidencias != null) {
            coincidencias.groups[1]?.value ?:"No encontrados"
        } else {
            "No encontrados"
        }
    }

}