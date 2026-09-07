package com.aynimascotas.unidad2.ruta1

fun imprimirResumenNotificaciones(cantidadNotificaciones: Int): String {
    return if (cantidadNotificaciones < 100) {
        "Tienes $cantidadNotificaciones notificaciones."
    } else {
        "¡Tu teléfono está ardiendo! Tienes 99+ notificaciones."
    }
}

fun calcularPrecioEntradaCine(edad: Int, esLunes: Boolean): Int {
    return when {
        edad in 0..12 -> 15
        edad in 13..60 -> if (esLunes) 25 else 30
        edad in 61..100 -> 20
        else -> -1
    }
}

fun convertirTemperatura(
    valorInicial: Double,
    unidadInicial: String,
    unidadFinal: String,
    formulaConversion: (Double) -> Double,
): String {
    val valorConvertido = formulaConversion(valorInicial)
    return "%.2f °%s son %.2f °%s.".format(valorInicial, unidadInicial, valorConvertido, unidadFinal)
}

class Cancion(
    val titulo: String,
    val artista: String,
    val anoPublicacion: Int,
    val reproducciones: Int,
) {
    val esPopular: Boolean
        get() = reproducciones >= 1000

    fun obtenerDescripcion(): String {
        val popularidad = if (esPopular) "Es popular." else "No es popular."
        return "$titulo, interpretada por $artista, se lanzó en $anoPublicacion. $popularidad"
    }
}

class PersonaPerfil(
    val nombre: String,
    val edad: Int,
    val pasatiempo: String?,
    val referente: PersonaPerfil?,
) {
    fun obtenerPerfil(): String {
        val textoPasatiempo = if (pasatiempo != null) "Le gusta $pasatiempo. " else "No tiene pasatiempo registrado. "
        val textoReferente = if (referente != null) {
            "Tiene un referente llamado ${referente.nombre}, a quien le gusta ${referente.pasatiempo ?: "nada en particular"}."
        } else {
            "No tiene un referente."
        }
        return "Nombre: $nombre\nEdad: $edad\n$textoPasatiempo$textoReferente"
    }
}

open class Telefono(var estaEncendidaLaPantalla: Boolean = false) {
    open fun encenderPantalla() {
        estaEncendidaLaPantalla = true
    }

    fun apagarPantalla() {
        estaEncendidaLaPantalla = false
    }

    fun obtenerEstadoPantalla(): String {
        return if (estaEncendidaLaPantalla) "La pantalla está encendida." else "La pantalla está apagada."
    }
}

class TelefonoPlegable(var estaPlegado: Boolean = true) : Telefono() {
    override fun encenderPantalla() {
        if (!estaPlegado) {
            estaEncendidaLaPantalla = true
        }
    }

    fun plegar() {
        estaPlegado = true
        apagarPantalla()
    }

    fun desplegar() {
        estaPlegado = false
    }
}

class OfertaSubasta(val monto: Int, val ofertante: String)

fun calcularPrecioSubasta(oferta: OfertaSubasta?, precioMinimo: Int): Int {
    return oferta?.monto ?: precioMinimo
}

fun ejecutarTodosLosEjercicios(): String {
    val resultado = StringBuilder()

    resultado.append("1. NOTIFICACIONES MÓVILES\n")
    resultado.append(imprimirResumenNotificaciones(51)).append("\n")
    resultado.append(imprimirResumenNotificaciones(135)).append("\n\n")

    resultado.append("2. PRECIO ENTRADAS CINE\n")
    resultado.append("Niño (10 años): $").append(calcularPrecioEntradaCine(10, false)).append("\n")
    resultado.append("Adulto (28 años, Lunes): $").append(calcularPrecioEntradaCine(28, true)).append("\n")
    resultado.append("Adulto mayor (65 años): $").append(calcularPrecioEntradaCine(65, false)).append("\n\n")

    resultado.append("3. CONVERSOR DE TEMPERATURA\n")
    resultado.append(convertirTemperatura(27.0, "C", "F") { (9.0 / 5.0 * it) + 32 }).append("\n")
    resultado.append(convertirTemperatura(350.0, "K", "C") { it - 273.15 }).append("\n")
    resultado.append(convertirTemperatura(100.0, "F", "K") { (5.0 / 9.0 * (it - 32)) + 273.15 }).append("\n\n")

    resultado.append("4. CATÁLOGO DE CANCIONES\n")
    val cancion = Cancion("Bohemian Rhapsody", "Queen", 1975, 1500000)
    resultado.append(cancion.obtenerDescripcion()).append("\n\n")

    resultado.append("5. PERFIL DE INTERNET\n")
    val amanda = PersonaPerfil("Amanda", 33, "Jugar tenis", null)
    val atiqah = PersonaPerfil("Atiqah", 28, "Escalar", amanda)
    resultado.append(amanda.obtenerPerfil()).append("\n---\n")
    resultado.append(atiqah.obtenerPerfil()).append("\n\n")

    resultado.append("6. TELÉFONO PLEGABLE\n")
    val telefonoPlegable = TelefonoPlegable()
    telefonoPlegable.encenderPantalla()
    resultado.append("Intentar encender plegado: ").append(telefonoPlegable.obtenerEstadoPantalla()).append("\n")
    telefonoPlegable.desplegar()
    telefonoPlegable.encenderPantalla()
    resultado.append("Encender desplegado: ").append(telefonoPlegable.obtenerEstadoPantalla()).append("\n\n")

    resultado.append("7. SUBASTA ESPECIAL\n")
    val ofertaEspecial = OfertaSubasta(10600, "Coleccionista A")
    resultado.append("Precio final con oferta: $").append(calcularPrecioSubasta(ofertaEspecial, 5000)).append("\n")
    resultado.append("Precio final sin oferta: $").append(calcularPrecioSubasta(null, 5000))

    return resultado.toString()
}
