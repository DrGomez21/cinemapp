package com.example.cinesapp.modelos

class DescripcionPelicula(content: String) {
    var srcPoster: String = obtenerUrlImagen(content)
        private set

    var sinopsis: String? = obtenerSinopsis(content)
        private set

    var trailer: String? = obtenerTrailer(content)
        private set

    private companion object {
        private val PATRON_URL_IMAGEN = Regex("""src="([^"]+\.(?:jpg|jpeg|png|gif|bmp)\?\d+)"""")
        private val PATRON_SINOPSIS = Regex("<h1>Sinopsis</h1>\\s*<p>(.*?)</p>")
        private val PATRON_TRAILER = Regex("<h1>Trailer</h1>\\s*<p>(.*?)</p>")

        private fun obtenerUrlImagen(texto: String): String {
            val coincidencias = PATRON_URL_IMAGEN.find(texto)
            return coincidencias?.run {
                val urlImagen = groupValues[1]
                urlImagen
            } ?: "http://cines.com.py/images/dinamico/peliculas/2974/afiche/wish-mediano.jpeg?1703259064"
        }

        private fun obtenerSinopsis(texto: String): String {
            val coincidencias = PATRON_SINOPSIS.find(texto)
            return if (coincidencias != null) {
                coincidencias.groups[1]?.value ?:"No se encontró la sinopsis"
            } else {
                "No se encontro la sinopsis"
            }
        }

        private fun obtenerTrailer(texto: String): String? {
            val coincidencias = PATRON_TRAILER.find(texto)
            return if (coincidencias != null) {
                coincidencias.groups[1]?.value ?: "No se encontró el trailer"
            } else {
                "No se encontró el trailer"
            }
        }
    }
}