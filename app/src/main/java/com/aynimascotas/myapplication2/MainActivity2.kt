package com.aynimascotas.myapplication2

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
import com.aynimascotas.myapplication.ui.theme.MyApplicationTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val explicacion = explicarRuta2()

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Angel",
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp),
                        infoText = explicacion,
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, infoText: String = "") {
    Text(
        text = "¡Hola, $name!\n\n$infoText",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting(name = "Ruta 2 - Android Studio", infoText = "Configuración básica de Empty Activity cargada.")
    }
}

fun explicarRuta2(): String {
    val proyecto = "MyApplication2"
    val plantilla = "Empty Activity"

    println("=== UNIDAD 1 - RUTA 2: CONFIGURACIÓN DE ANDROID STUDIO ===")
    println("Proyecto activo: $proyecto (Plantilla: $plantilla)")

    val resumen = "Configuración básica de $plantilla cargada en $proyecto."
    println(resumen)
    return resumen
}
