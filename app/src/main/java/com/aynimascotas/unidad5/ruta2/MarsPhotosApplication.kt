package com.aynimascotas.unidad5.ruta2

import android.app.Application
import com.aynimascotas.unidad5.ruta2.data.AppContainer
import com.aynimascotas.unidad5.ruta2.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
