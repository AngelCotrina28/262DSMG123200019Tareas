package com.aynimascotas.unidad6.ruta1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aynimascotas.ui.theme.MyApplicationTheme
import com.aynimascotas.unidad6.ruta1.ui.DessertReleaseApp

class MainActivityUnidad6Ruta1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                DessertReleaseApp()
            }
        }
    }
}
