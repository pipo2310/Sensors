package com.example.myapplication123

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class modificarSensores : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_sensores)

        val b = intent.extras
        val id = b!!.getString("nombre")
        var nombre = findViewById<EditText>(R.id.editText2);
        var tipo = findViewById<EditText>(R.id.tipo);
        var unidad = findViewById<EditText>(R.id.editText5);
        nombre.hint=id
        tipo.hint=id
        unidad.hint=id
        //nombre.setText(id)
        //tipo.setText(id)
        //unidad.setText(id)
    }
}
