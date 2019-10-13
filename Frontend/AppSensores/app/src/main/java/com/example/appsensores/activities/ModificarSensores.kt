package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appsensores.*
import com.example.appsensores.models.Sensor
import com.example.appsensores.services.SensoresService
import com.example.appsensores.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_crear_sensores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModificarSensores : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_sensores)
        setSupportActionBar(toolbar)

       // val b = intent.extras
        //val id = b!!.getString("nombre")
        var nombre = findViewById<EditText>(R.id.editText2);
        var tipo = findViewById<EditText>(R.id.tipo);
        var unidad = findViewById<EditText>(R.id.editText5);
        //nombre.hint=id
        //tipo.hint=id
       // unidad.hint=id
        var modificar=findViewById<Button>(R.id.button)
        modificar.setOnClickListener {
            //Prametros para la llamda a la base de update
            //nombre.text
            //tipo.text
            //unidad.text
            val sensoresService: SensoresService =
                ServiceBuilder.buildService(
                    SensoresService::class.java
                )

            //val requestCall: Call<Sensor> = jsonPlaceHolderApi.actualizarSensores(3,"Sensor de Gas 1000",1,"mg/m3",1,10.0,100.0)
            val sensorAActualizar= Sensor()
            sensorAActualizar.nombre="Sensor de Gas 1000"
            sensorAActualizar.unidad="mg/m3"
            sensorAActualizar.isAlerta_roja=100.0
            sensorAActualizar.isAlerta_amarilla=10.0
            sensorAActualizar.tipo=1
            sensorAActualizar.id_cuenta=1

            val requestCall: Call<Sensor> = sensoresService.updateSensores(sensorAActualizar,3)

            requestCall.enqueue(object: Callback<Sensor>{
                override fun onResponse(call: Call<Sensor>, response: Response<Sensor>) {
                    if (response.isSuccessful()){
                        finish()
                        Log.e("Hola","HOlanss");
                        Toast.makeText(this@ModificarSensores,
                            "Sensor Actualizado Exitosamente",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@ModificarSensores,
                            "No se pudo actualizar",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Sensor>?, t: Throwable?) {
                    Toast.makeText(this@ModificarSensores,
                    "No se pudo actualizar",Toast.LENGTH_SHORT).show()}
            }
            )
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
