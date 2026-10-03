package com.aynimascotas.unidad6.ruta3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class BusSchedule(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "stop_name")
    val stopName: String,
    @ColumnInfo(name = "arrival_time_timestamp")
    val arrivalTimeTimestamp: Int,
)
