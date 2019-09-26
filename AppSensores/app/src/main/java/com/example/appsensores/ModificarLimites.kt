package com.example.appsensores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

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

        val btnAceptar = findViewById<Button>(R.id.btn_aceptar)
        btnAceptar.setOnClickListener{
            intent = Intent(this, Semaforos::class.java)
            intent.putExtra("yellowlimA", yellowlimA)
            startActivity(intent)
        }
    }

    fun getyellowlimA(): Int{
        return yellowlimA
    }

    fun getredlimA(): Int{
        return redlimA
    }

    fun getyyellowlimG(): Int{
        return yellowlimG
    }

    fun getredlimG(): Int{
        return redlimG
    }

    fun getyellowlimE(): Int{
        return yellowlimE
    }

    fun getredlimE(): Int{
        return redlimE
    }
}
