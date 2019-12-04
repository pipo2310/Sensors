package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.appsensores.R
import com.example.appsensores.models.Sensor
import com.example.appsensores.services.SensoresService
import com.example.appsensores.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_crear_sensores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallesSensor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_sensor)
        toolbar.title = "Informacion del Sensor"
        setSupportActionBar(toolbar)
        val invalido = -1.toLong()
        var idSensor = invalido
        idSensor = intent.getLongExtra("idSensor", idSensor)
        if(idSensor == invalido) {
            intent = Intent(this, VistaSensores::class.java)
            startActivity(intent)
        }else {
            val sensoresService: SensoresService = ServiceBuilder.buildService(
                SensoresService::class.java)

            val call: Call<Sensor> = sensoresService.getSensor(idSensor)

            call.enqueue(object: Callback<Sensor> {
                override fun onResponse(call: Call<Sensor>, response: Response<Sensor>) {
                    if (!response.isSuccessful())
                    {
                        // Mensaje de Error
                        return
                    }

                    val sensor = response.body()
                    llenarCampos(sensor,idSensor)
                }
                override fun onFailure(call: Call<Sensor>, t:Throwable) {
                    // Mensaje de Error
                    startActivity(intent)
                }
            })
        }
    }

    fun llenarCampos(sensor:Sensor,idSensor: Long){


        val nombreSensor = findViewById<TextView>(R.id.nombreSensor)
        nombreSensor.setText(sensor.nombre.toString())
        val tipoSensor = findViewById<TextView>(R.id.tipoSensor)
        //tipoSensor.setText(sensor.tipo.toString())
        if(sensor.tipo==1){
            tipoSensor.setText("Gas")
        }else if (sensor.tipo==2){
            tipoSensor.setText("Electricidad")
        }else if(sensor.tipo==3){
            tipoSensor.setText("Agua")
        }
        val unidadSensor = findViewById<TextView>(R.id.unidadSensor)
        unidadSensor.setText(sensor.unidad.toString())



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
        }else if(item.itemId == R.id.historicos){
            intent = Intent(this, Historicos::class.java)
            startActivity(intent)
        }else if (item.itemId == R.id.empresas)
        {
            intent = Intent(this, ListaDeEmpresas::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }else{
            super.onOptionsItemSelected(item)
        }
        return true
    }
}
