package com.aynimascotas.unidad6.ruta3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aynimascotas.unidad6.ruta2.InventoryApplication
import com.aynimascotas.unidad6.ruta3.data.BusScheduleDao
import com.aynimascotas.unidad6.ruta3.data.BusScheduleDatabase
import com.aynimascotas.unidad6.ruta3.model.BusSchedule
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val busScheduleDao: BusScheduleDao) : ViewModel() {
    fun getFullSchedule(): Flow<List<BusSchedule>> = busScheduleDao.getAllSchedules()

    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = busScheduleDao.getScheduleFor(stopName)

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as InventoryApplication)
                val database = BusScheduleDatabase.getDatabase(application)
                BusScheduleViewModel(database.busScheduleDao())
            }
        }
    }
}
