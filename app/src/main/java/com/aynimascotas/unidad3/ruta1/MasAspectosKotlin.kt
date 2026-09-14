package com.aynimascotas.unidad3.ruta1

enum class Dificultad {
    FACIL,
    MEDIO,
    DIFICIL,
}

data class Pregunta<T>(
    val enunciado: String,
    val respuesta: T,
    val dificultad: Dificultad,
)

class Cuestionario {
    val pregunta1 = Pregunta("¿De qué color es el cielo?", "Azul", Dificultad.FACIL)
    val pregunta2 = Pregunta("¿Cuántos planetas tiene el sistema solar?", 8, Dificultad.MEDIO)
    val pregunta3 = Pregunta("¿Verdadero o falso: Kotlin es un lenguaje de programación?", true, Dificultad.FACIL)

    val preguntas = listOf(pregunta1, pregunta2, pregunta3)

    fun obtenerPreguntaFormateada(pregunta: Pregunta<*>): String {
        return "Pregunta [${pregunta.dificultad}]: ${pregunta.enunciado} -> Respuesta: ${pregunta.respuesta}"
    }
}

fun ejecutarDemostracionColeccionesYGenericos(): String {
    val resultado = StringBuilder()
    val cuestionario = Cuestionario()

    resultado.append("--- 1. CLASES GENÉRICAS Y ENUMS ---\n")
    cuestionario.preguntas.forEach { pregunta ->
        resultado.append(cuestionario.obtenerPreguntaFormateada(pregunta)).append("\n")
    }

    resultado.append("\n--- 2. COLECCIONES: LISTAS Y FILTRADO ---\n")
    val numeros = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numerosPares = numeros.filter { it % 2 == 0 }
    val cuadrados = numerosPares.map { it * it }
    resultado.append("Números originales: ").append(numeros).append("\n")
    resultado.append("Filtro números pares: ").append(numerosPares).append("\n")
    resultado.append("Transformación al cuadrado: ").append(cuadrados).append("\n")

    resultado.append("\n--- 3. CONJUNTOS (SET) Y MAPAS (MAP) ---\n")
    val lenguajesUnicos = setOf("Kotlin", "Java", "Kotlin", "Swift", "Java")
    val mapaCapitales = mapOf("Perú" to "Lima", "España" to "Madrid", "México" to "Ciudad de México")

    resultado.append("Conjunto único (Set): ").append(lenguajesUnicos).append("\n")
    resultado.append("Mapa País -> Capital: ").append(mapaCapitales).append("\n")

    resultado.append("\n--- 4. FUNCIONES DE ORDEN SUPERIOR (GROUPBY Y FOLD) ---\n")
    val agrupadasPorDificultad = cuestionario.preguntas.groupBy { it.dificultad }
    resultado.append("Preguntas agrupadas por dificultad: ").append(agrupadasPorDificultad.keys).append("\n")

    val sumaTotalNumeros = numeros.fold(0) { acumulador, elemento -> acumulador + elemento }
    resultado.append("Suma total acumulada con fold: ").append(sumaTotalNumeros)

    return resultado.toString()
}
