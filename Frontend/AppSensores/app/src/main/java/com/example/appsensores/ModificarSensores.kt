package com.example.appsensores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.appsensores.R
import com.example.appsensores.Semaforos
import kotlinx.android.synthetic.main.activity_crear_sensores.*

class ModificarSensores : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_sensores)
        setSupportActionBar(toolbar)

        val b = intent.extras
        val id = b!!.getString("nombre")
        var nombre = findViewById<EditText>(R.id.editText2);
        var tipo = findViewById<EditText>(R.id.tipo);
        var unidad = findViewById<EditText>(R.id.editText5);
        nombre.hint=id
        tipo.hint=id
        unidad.hint=id
        var modificar=findViewById<Button>(R.id.button)
        modificar.setOnClickListener {
            //Prametros para la llamda a la base de update
            //nombre.text
            //tipo.text
            //unidad.text
            intent = Intent(this, VistaSensores::class.java)
            //pasar los 3 elementos del objeto como extras separados y recuperarlos del otro lado con el "" que sea pertinente
            startActivity(intent);
        }
        //nombre.setText(id)
        //tipo.setText(id)
        //unidad.setText(id)
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
            startActivity(intent)

        }else{
            super.onOptionsItemSelected(item)
        }
        return true
    }
}
