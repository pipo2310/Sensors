package com.example.appsensores.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.EditText
import com.example.appsensores.R


class Costos : AppCompatActivity() {

    var costo_agua = 0
    var costo_elec = 0
    var costo_gas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.appsensores.R.layout.activity_costos)



        val minA = findViewById<EditText>(R.id.costo_agua)
        minA.setText(costo_agua.toString())

    }

    fun sendFeedback(button: View) {
        val costo_aguaField = findViewById(com.example.appsensores.R.id.costo_agua) as EditText
        costo_agua = costo_aguaField.text.toString().toInt()
        val costo_elecField = findViewById(com.example.appsensores.R.id.costo_elec) as EditText
        costo_elec = costo_elecField.text.toString().toInt()
        val costo_gasField = findViewById(com.example.appsensores.R.id.costo_gas) as EditText
        costo_gas = costo_gasField.text.toString().toInt()
    }


}
