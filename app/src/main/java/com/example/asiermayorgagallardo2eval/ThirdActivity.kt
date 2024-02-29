package com.example.asiermayorgagallardo2eval

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var listaVideojuego: ArrayList<Videojuego>
    private lateinit var videojuego: Videojuego

    private lateinit var mostrado: TextView
    private lateinit var crear: Button
    private lateinit var volver: Button
    private lateinit var guardado: Button

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        listaVideojuego = ArrayList()
        videojuego = intent.getParcelableExtra("videojuego")!!
        listaVideojuego = intent.getParcelableArrayListExtra("lista")!!
        listaVideojuego.add(videojuego)

        mostrado = findViewById(R.id.mostradoVideojuegos)
        crear = findViewById(R.id.crearThird)
        volver = findViewById(R.id.volverThird)
        guardado = findViewById(R.id.guardadoThird)

        listaVideojuego.forEach {
            mostrado.text = "${mostrado.text}$it\n\n"
        }

        crear.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            Log.d("mostrado lista", listaVideojuego.toString())
            intent.putExtra("lista", listaVideojuego)
            startActivity(intent)
        }

        volver.setOnClickListener {
            listaVideojuego.removeAt(listaVideojuego.lastIndex)
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("lista", listaVideojuego)
            intent.putExtra("videojuego", videojuego)
            startActivity(intent)
        }

        guardado.setOnClickListener {
            val dbHelper = DatabaseHelper.DatabaseHelper(this)
            listaVideojuego.forEach {
                dbHelper.escribir(it)
            }
            val intent = Intent(this, DatabaseHelper.SaveActivity::class.java)
            intent.putExtra("lista", listaVideojuego)
            startActivity(intent)
        }

    }
}
