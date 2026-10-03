package com.aynimascotas.unidad6.ruta3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aynimascotas.ui.theme.MyApplicationTheme
import com.aynimascotas.unidad6.ruta3.ui.BusScheduleApp

class MainActivityUnidad6Ruta3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                BusScheduleApp()
            }
        }
    }
}
