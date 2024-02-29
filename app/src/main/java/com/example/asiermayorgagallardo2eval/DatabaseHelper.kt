package com.example.asiermayorgagallardo2eval


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DatabaseHelper {

    class SaveActivity : AppCompatActivity() {

        private lateinit var listaVideojuegos: ArrayList<Videojuego>

        private lateinit var volver: Button
        private lateinit var mostrado: TextView

        @SuppressLint("MissingInflatedId", "SetTextI18n")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_save)
            //Creamos la instancia de la clase DatabaseHelper, nos va a permitir acceder a los recursos de la aplicación
            val dbHelper = DatabaseHelper(this)

            listaVideojuegos = intent.getParcelableArrayListExtra("lista")!!
            volver = findViewById(R.id.volverSave)
            mostrado = findViewById(R.id.mostradoSave)

            dbHelper.lectura().forEach {
                mostrado.text = "${mostrado.text}$it\n\n"
            }

            volver.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("lista", listaVideojuegos)
                startActivity(intent)
            }

        }
    }

    // Clase DatabaseHelper
    class DatabaseHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
        companion object {
            private const val DATABASE_VERSION = 3
            private const val DATABASE = "JuegosComprados.db"
            private const val TABLA_VIDEOJUEGOS = "videojuegos"
            private const val KEY_ID = "id"
            private const val COLUMN_NOMBRE = "nombre"
            private const val COLUMN_VALORACION = "valoracion"
            private const val COLUMN_EMPRESA = "empresa"
            private const val COLUMN_ANIO = "anio"

        }

        override fun onCreate(db: SQLiteDatabase) {
            val createTable = "CREATE TABLE $TABLA_VIDEOJUEGOS (" +
                    "$KEY_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_NOMBRE TEXT," +
                    "$COLUMN_VALORACION FLOAT," +
                    "$COLUMN_EMPRESA TEXT," +
                    "$COLUMN_ANIO INTEGER)"
            db.execSQL(createTable)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLA_VIDEOJUEGOS")
            onCreate(db)
        }

        fun escribir(videojuego: Videojuego): Long {
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NOMBRE, videojuego.getNombre())
                put(COLUMN_VALORACION, videojuego.getValoracion())
                put(COLUMN_EMPRESA, videojuego.getEmpresa())
                put(COLUMN_ANIO, videojuego.getAnio())
            }

            val id = db.insert(TABLA_VIDEOJUEGOS, null, values)
            db.close()
            return id
        }


        @SuppressLint("Range")
        fun lectura(): ArrayList<Videojuego> {
            val lectura = ArrayList<Videojuego>()
            val selectQuery = "SELECT * FROM $TABLA_VIDEOJUEGOS"
            val db = this.readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
//                    val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                    val valoracion = cursor.getFloat(cursor.getColumnIndex(COLUMN_VALORACION))
                    val empresa = cursor.getString(cursor.getColumnIndex(COLUMN_EMPRESA))
                    val anio = cursor.getInt(cursor.getColumnIndex(COLUMN_ANIO))
                    lectura.add(Videojuego(nombre, valoracion, empresa, anio))
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return lectura
        }

    }


}