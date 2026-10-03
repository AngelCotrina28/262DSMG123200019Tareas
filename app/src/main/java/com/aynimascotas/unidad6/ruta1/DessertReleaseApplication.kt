package com.aynimascotas.unidad6.ruta1

import android.app.Application
import com.aynimascotas.unidad5.ruta2.data.AppContainer
import com.aynimascotas.unidad5.ruta2.data.DefaultAppContainer
import com.aynimascotas.unidad6.ruta1.data.SqlBasicsDatabaseHelper
import com.aynimascotas.unidad6.ruta1.data.UserPreferencesRepository
import com.aynimascotas.unidad6.ruta1.data.dataStore

class DessertReleaseApplication : Application() {
    lateinit var container: AppContainer
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        userPreferencesRepository = UserPreferencesRepository(dataStore)

        val dbHelper = SqlBasicsDatabaseHelper(this)
        dbHelper.writableDatabase
    }
}
