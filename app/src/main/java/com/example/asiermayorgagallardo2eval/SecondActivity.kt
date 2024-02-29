package com.example.asiermayorgagallardo2eval

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    private lateinit var volver: Button
    private lateinit var continuar: Button

    private lateinit var editEmpresa: EditText
    private lateinit var editAnio: EditText

    private lateinit var listaVideojuegos: ArrayList<Videojuego>
    private lateinit var videojuego: Videojuego
    private lateinit var empresa: String
    private var anio= 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        videojuego = intent.getParcelableExtra("videojuego")!!
        listaVideojuegos = intent.getParcelableArrayListExtra("lista")!!

        editEmpresa = findViewById(R.id.editEmpresa)
        editAnio = findViewById(R.id.editAnio)
        continuar = findViewById(R.id.continuarSecond)
        volver = findViewById(R.id.volverSecond)

        continuar.setOnClickListener {
            empresa = editEmpresa.text.toString().trim()
            anio = editAnio.text.toString().toInt()
            if(!(empresa.isEmpty() || anio < 1900)) {
                videojuego.setEmpresa(empresa)
                videojuego.setAnio(anio)

                val intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("lista",listaVideojuegos)
                intent.putExtra("videojuego",videojuego)
                startActivity(intent)
            }
        }

        volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lista",listaVideojuegos)
            intent.putExtra("videojuego",videojuego)
            startActivity(intent)
        }

    }
}
