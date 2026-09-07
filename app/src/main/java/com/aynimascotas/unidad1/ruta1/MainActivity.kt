package com.aynimascotas.unidad1.ruta1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aynimascotas.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val resultadoTarjeta = explicarYGenerarTarjeta()

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        text = resultadoTarjeta,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting(text = "¡Feliz Cumpleaños, Héctor!\n¡Ya tienes 20 años llenos de alegrías!\nCon mucho cariño, Valeria.")
    }
}

fun explicarYGenerarTarjeta(): String {
    val destinatario = "Héctor"
    val edadExplicita = 20
    val impresionesRealizadas = 1

    println("--- Tarjeta #$impresionesRealizadas ---")
    println("Preparando felicitación para $destinatario (${edadExplicita + 1} años el próximo año)...")

    val tarjeta = generarTarjetaCumpleanos(
        nombreDestinatario = destinatario,
        remitente = "Angel",
        edad = edadExplicita,
    )

    println("\n" + tarjeta)
    return tarjeta
}

fun generarTarjetaCumpleanos(
    nombreDestinatario: String,
    remitente: String,
    edad: Int,
    ocasion: String = "Cumpleaños",
): String {
    val borde = "=========================================="
    val encabezado = "¡Feliz $ocasion, $nombreDestinatario!"
    val cuerpo = "¡Ya tienes $edad años llenos de alegrías!"
    val firma = "Con mucho cariño, $remitente."

    return "$borde\n$encabezado\n$cuerpo\n$firma\n$borde"
}
