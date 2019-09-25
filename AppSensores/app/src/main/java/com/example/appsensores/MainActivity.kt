package com.example.appsensores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn_sem)
        btn.setOnClickListener {
            // Set the progress status zero on each button click
            intent = Intent(this, Semaforos::class.java)
            startActivity(intent)
        }
    }
}
