package com.aynimascotas.unidad6.ruta3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aynimascotas.unidad6.ruta3.model.BusSchedule

@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class BusScheduleDatabase : RoomDatabase() {
    abstract fun busScheduleDao(): BusScheduleDao

    companion object {
        @Volatile
        private var Instance: BusScheduleDatabase? = null

        fun getDatabase(context: Context): BusScheduleDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BusScheduleDatabase::class.java, "bus_schedule.db")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            db.execSQL("""
                                INSERT INTO schedule (id, stop_name, arrival_time_timestamp) VALUES
                                (1, 'Estación Central', 1680000000),
                                (2, 'Estación Norte', 1680003600),
                                (3, 'Estación Sur', 1680007200),
                                (4, 'Aeropuerto Jorge Chávez', 1680010800),
                                (5, 'Universidad Nacional', 1680014400),
                                (6, 'Estación Central', 1680018000),
                                (7, 'Estación Norte', 1680021600)
                            """.trimIndent())
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
