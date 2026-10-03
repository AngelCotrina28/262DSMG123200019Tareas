package com.aynimascotas.unidad6.ruta1.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlBasicsDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "email.db"
        const val DATABASE_VERSION = 1
        const val TABLE_EMAIL = "email"
        const val COLUMN_ID = "id"
        const val COLUMN_SUBJECT = "subject"
        const val COLUMN_SENDER = "sender"
        const val COLUMN_FOLDER = "folder"
        const val COLUMN_READ = "read"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_EMAIL (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_SUBJECT TEXT NOT NULL,
                $COLUMN_SENDER TEXT NOT NULL,
                $COLUMN_FOLDER TEXT NOT NULL,
                $COLUMN_READ INTEGER NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)

        val insertQuery = """
            INSERT INTO $TABLE_EMAIL ($COLUMN_SUBJECT, $COLUMN_SENDER, $COLUMN_FOLDER, $COLUMN_READ) VALUES
            ('Bienvenido al curso de Android', 'soporte@android.com', 'inbox', 1),
            ('Reunión de equipo hoy a las 4pm', 'gerente@empresa.com', 'inbox', 0),
            ('Confirmación de compra #1029', 'ventas@tienda.com', 'inbox', 1),
            ('Oferta de trabajo: Desarrollador Android', 'rrhh@tech.com', 'spam', 0),
            ('Recordatorio de pago de servicios', 'notificaciones@banco.com', 'inbox', 0),
            ('Resumen de actividad semanal', 'newsletter@dev.org', 'inbox', 1)
        """.trimIndent()
        db.execSQL(insertQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EMAIL")
        onCreate(db)
    }
}
