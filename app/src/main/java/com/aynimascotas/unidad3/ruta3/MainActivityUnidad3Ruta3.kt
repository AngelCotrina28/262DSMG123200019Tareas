package com.aynimascotas.unidad3.ruta3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.aynimascotas.ui.theme.MyApplicationTheme

class MainActivityUnidad3Ruta3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                PantallaUnidad3Ruta3()
            }
        }
    }
}

@Composable
fun PantallaUnidad3Ruta3() {
    var pestanaSeleccionada by remember { mutableIntStateOf(0) }
    val titulosPestanas = listOf("App Woof", "30 Días de Bienestar")

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = pestanaSeleccionada) {
                titulosPestanas.forEachIndexed { indice, titulo ->
                    Tab(
                        selected = pestanaSeleccionada == indice,
                        onClick = { pestanaSeleccionada = indice },
                        text = { Text(text = titulo, style = MaterialTheme.typography.titleMedium) },
                    )
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (pestanaSeleccionada) {
                0 -> WoofApp()
                1 -> ThirtyDaysApp()
            }
        }
    }
}
