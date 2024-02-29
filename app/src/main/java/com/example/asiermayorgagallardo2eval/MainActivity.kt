package com.example.asiermayorgagallardo2eval

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var videojuego: Videojuego
    private lateinit var listaVideojuegos: ArrayList<Videojuego>

    private lateinit var editNombre: EditText
    private lateinit var editValoracion: EditText
    private lateinit var continuar: Button

    private lateinit var nombre: String
    private var valoracion = 0.0.toFloat()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNombre = findViewById(R.id.editNombre)
        editValoracion = findViewById(R.id.editValoracion)
        continuar = findViewById(R.id.continuarMain)


        listaVideojuegos = intent.getParcelableArrayListExtra("lista") ?: arrayListOf()


        videojuego = Videojuego("", 0.0.toFloat(), "", 0)

        continuar.setOnClickListener {
            nombre = editNombre.text.toString()
            valoracion = editValoracion.text.toString().trim().toFloat()

            if (!(nombre.isEmpty() || valoracion.isNaN() || valoracion < 0.0)) {
                videojuego.setNombre(nombre)
                videojuego.setValoracion(valoracion)

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("videojuego", videojuego)
                intent.putExtra("lista", listaVideojuegos)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error al pasar de actividad", Toast.LENGTH_SHORT).show()
            }
        }

    }
}