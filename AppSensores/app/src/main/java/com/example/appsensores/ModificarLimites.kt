package com.example.appsensores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ModificarLimites : AppCompatActivity() {
    var yellowlimA = 0
    var redlimA = 0
    var yellowlimG = 0
    var redlimG = 0
    var yellowlimE = 0
    var redlimE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_limites)
    }

    override fun onResume() {
        super.onResume()
        yellowlimA = intent.getIntExtra("yellowlimA", yellowlimA)
        var text1 = findViewById<TextView>(R.id.titulo)
        text1.text = yellowlimA.toString()
        redlimA = intent.getIntExtra("redlimA", redlimA)
        yellowlimG = intent.getIntExtra("yellowlimG", yellowlimG)
        redlimG = intent.getIntExtra("redlimG", redlimG)
        yellowlimE = intent.getIntExtra("yellowlimE", yellowlimE)
        redlimE = intent.getIntExtra("redlimE", redlimE)

        val minA = findViewById<EditText>(R.id.min_agua)
        val maxA = findViewById<EditText>(R.id.max_agua)
        val minG = findViewById<EditText>(R.id.min_gas)
        val maxG = findViewById<EditText>(R.id.max_gas)
        val minE = findViewById<EditText>(R.id.min_elect)
        val maxE = findViewById<EditText>(R.id.max_elect)

        val btnAceptar = findViewById<Button>(R.id.btn_aceptar)
        btnAceptar.setOnClickListener{
            yellowlimA = minA.text.toString().toInt()
            redlimA = maxA.text.toString().toInt()
            yellowlimG = minG.text.toString().toInt()
            redlimG = maxG.text.toString().toInt()
            yellowlimE = minE.text.toString().toInt()
            redlimE = maxE.text.toString().toInt()
            intent = Intent(this, Semaforos::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

            intent.putExtra("yellowlimA", yellowlimA)
            intent.putExtra("redlimA", redlimA)
            intent.putExtra("yellowlimG", yellowlimG)
            intent.putExtra("redlimG", redlimG)
            intent.putExtra("yellowlimE", yellowlimE)
            intent.putExtra("redlimE", redlimE)
            startActivity(intent)
        }
    }
}
