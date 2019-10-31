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

        val b = intent.extras
        val name = b!!.getString("nombre")
        val unit = b!!.getString("unidad")
        val type = b!!.getInt("tipo")
        val id = b!!.getLong("id")
        val id_cuenta = b!!.getLong("id_cuenta")
        val nombre = findViewById<EditText>(R.id.editText2);
        val tipo = findViewById<EditText>(R.id.tipo);
        val unidad = findViewById<EditText>(R.id.editText5);

        nombre.setText(name)
        unidad.setText(unit)
        if(type==1){
            tipo.setText("Gas")

        }else if (type==2){
            tipo.setText("Electricidad")
        }else if(type==3){
            tipo.setText("Agua")
        }



        var modificar=findViewById<Button>(R.id.button)
        modificar.setOnClickListener {

            val sensoresService: SensoresService =
                ServiceBuilder.buildService(
                    SensoresService::class.java
                )

            //val requestCall: Call<Sensor> = jsonPlaceHolderApi.actualizarSensores(3,"Sensor de Gas 1000",1,"mg/m3",1,10.0,100.0)
            val sensorAActualizar= Sensor()

            sensorAActualizar.nombre=nombre.text.toString()
            sensorAActualizar.unidad=unidad.text.toString()
            sensorAActualizar.id_cuenta=id_cuenta
           // sensorAActualizar.isAlerta_roja=100.0
            //sensorAActualizar.isAlerta_amarilla=10.0
            if (tipo.text.toString()=="Agua"){
                sensorAActualizar.tipo=3
            }else if (tipo.text.toString()=="Electricidad"){
                sensorAActualizar.tipo=2

            }else if (tipo.text.toString()=="Gas"){
                sensorAActualizar.tipo=1
            }

            intent = Intent(this, VistaSensores::class.java)
            val requestCall: Call<Sensor> = sensoresService.updateSensores(sensorAActualizar,id)


            requestCall.enqueue(object: Callback<Sensor>{
                override fun onResponse(call: Call<Sensor>, response: Response<Sensor>) {
                    if (response.isSuccessful()){
                        finish()
                        startActivity(intent);
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

        }else if(item.itemId == R.id.historicos){
            intent = Intent(this, Historicos::class.java)
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
