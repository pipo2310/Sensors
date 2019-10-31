package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.example.appsensores.R
import kotlinx.android.synthetic.main.activity_vista_sensores.*

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
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        yellowlimA = intent.getIntExtra("yellowlimA", yellowlimA)
        redlimA = intent.getIntExtra("redlimA", redlimA)
        yellowlimG = intent.getIntExtra("yellowlimG", yellowlimG)
        redlimG = intent.getIntExtra("redlimG", redlimG)
        yellowlimE = intent.getIntExtra("yellowlimE", yellowlimE)
        redlimE = intent.getIntExtra("redlimE", redlimE)

        val minA = findViewById<EditText>(R.id.min_agua)
        minA.setText(yellowlimA.toString())
        val maxA = findViewById<EditText>(R.id.max_agua)
        maxA.setText(redlimA.toString())
        val minG = findViewById<EditText>(R.id.min_gas)
        minG.setText(yellowlimG.toString())
        val maxG = findViewById<EditText>(R.id.max_gas)
        maxG.setText(redlimG.toString())
        val minE = findViewById<EditText>(R.id.min_elect)
        minE.setText(yellowlimE.toString())
        val maxE = findViewById<EditText>(R.id.max_elect)
        maxE.setText(redlimE.toString())

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

            intent.putExtra("yLA", yellowlimA)
            intent.putExtra("rLA", redlimA)
            intent.putExtra("yLG", yellowlimG)
            intent.putExtra("rLG", redlimG)
            intent.putExtra("yLR", yellowlimE)
            intent.putExtra("rLR", redlimE)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.itemId == R.id.sensores){
            intent = Intent(this, VistaSensores::class.java)
            startActivity(intent)
        }else if (item.itemId == R.id.semaforos)
        {
            intent = Intent(this, Semaforos::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }else if (item.itemId == R.id.costos)
        {
            intent = Intent(this, Costos::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }else if(item.itemId == R.id.cerrar_sesion){
            finishAffinity()
            setResult(R.id.cerrar_sesion)
            startActivity(Intent(this, IniciarSesion::class.java))
        }else{
            super.onOptionsItemSelected(item)
        }
        return true
    }
}
