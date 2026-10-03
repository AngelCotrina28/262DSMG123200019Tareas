package com.aynimascotas.unidad6.ruta3.data

import androidx.room.Dao
import androidx.room.Query
import com.aynimascotas.unidad6.ruta3.model.BusSchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM schedule ORDER BY arrival_time_timestamp ASC")
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time_timestamp ASC")
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>>
}
