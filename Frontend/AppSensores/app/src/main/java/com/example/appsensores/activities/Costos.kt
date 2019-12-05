package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.view.View
import android.widget.EditText
import com.example.appsensores.R
import com.example.appsensores.models.Medicion
import com.example.appsensores.models.TipoSensor
import com.example.appsensores.services.MedicionesService
import com.example.appsensores.services.TiposSensorService
import com.example.appsensores.utilities.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_vista_sensores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Costos : AppCompatActivity() {
    private val c: Constants = Constants()
    var tipos_sensores = emptyList<TipoSensor>()
    var costo_agua = 0.0F
    var costo_elec = 0.0F
    var costo_gas = 0.0F
    var index_agua = 0
    var index_elec = 0
    var index_gas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costos)
        toolbar.title = "Modificar Costos"
        setSupportActionBar(toolbar)

        intent = Intent(this, Costos::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

        getData()


    }

    fun sendFeedback(button: View) {
        val costo_aguaField = findViewById(R.id.costo_agua) as EditText
        costo_agua = costo_aguaField.text.toString().toFloat()
        val costo_elecField = findViewById(R.id.costo_elec) as EditText
        costo_elec = costo_elecField.text.toString().toFloat()
        val costo_gasField = findViewById(R.id.costo_gas) as EditText
        costo_gas = costo_gasField.text.toString().toFloat()

        if (tipos_sensores[index_agua].costo != costo_agua){
            tipos_sensores[index_agua].costo = costo_agua
            putData(tipos_sensores[index_agua],tipos_sensores[index_agua].id_tipo)
        }

        if (tipos_sensores[index_gas].costo != costo_gas){
            tipos_sensores[index_gas].costo = costo_gas
            putData(tipos_sensores[index_gas],tipos_sensores[index_gas].id_tipo)
        }

        if (tipos_sensores[index_elec].costo != costo_elec){
            tipos_sensores[index_elec].costo = costo_elec
            putData(tipos_sensores[index_elec],tipos_sensores[index_elec].id_tipo)
        }

    }

    private fun getData(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-34-235-147-100.compute-1.amazonaws.com/sensores/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tiposensorService: TiposSensorService = retrofit.create(TiposSensorService::class.java)


        val call: Call<List<TipoSensor>> = tiposensorService.tiposSensor

        call.enqueue(object: Callback<List<TipoSensor>> {
            override fun onResponse(call: Call<List<TipoSensor>>, response: Response<List<TipoSensor>>) {
                if (!response.isSuccessful())
                {
                    return
                }
                tipos_sensores = response.body()

                index_agua = tipos_sensores.firstIndexOrNull { it.nombre == "Agua"}
                index_elec = tipos_sensores.firstIndexOrNull { it.nombre == "Electricidad"}
                index_gas = tipos_sensores.firstIndexOrNull { it.nombre == "Gas"}

                if (tipos_sensores[index_agua].costo != null){
                    costo_agua = tipos_sensores[index_agua].costo
                    findViewById<EditText>(R.id.costo_agua).setText(costo_agua.toString())
                } else findViewById<EditText>(R.id.costo_agua).setText("0")

                if (tipos_sensores[index_elec].costo != null){
                    costo_elec = tipos_sensores[index_elec].costo
                    findViewById<EditText>(R.id.costo_elec).setText(costo_elec.toString())
                } else findViewById<EditText>(R.id.costo_elec).setText("0")

                if (tipos_sensores[index_gas].costo != null){
                    costo_gas = tipos_sensores[index_gas].costo
                    findViewById<EditText>(R.id.costo_gas).setText(costo_gas.toString())
                } else findViewById<EditText>(R.id.costo_gas).setText("0")

            }

            override fun onFailure(call: Call<List<TipoSensor>>?, t: Throwable?) {

            }
        })

    }

    private fun putData(tipo_sensor: TipoSensor, id: Int){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tiposensorService: TiposSensorService = retrofit.create(TiposSensorService::class.java)

        val call: Call<TipoSensor> = tiposensorService.updateTipoSensor(id, tipo_sensor)

        call.enqueue(object: Callback<TipoSensor> {
            override fun onResponse(call: Call<TipoSensor>, response: Response<TipoSensor>) {
                if (!response.isSuccessful())
                {
                    return
                }
            }

            override fun onFailure(call: Call<TipoSensor>?, t: Throwable?) {
            }
        })

    }

    private fun <T> Iterable<T>.firstIndexOrNull(predicate: (T) -> Boolean): Int {
        return this.mapIndexed { index, item -> Pair(index, item) }
            .first() { predicate(it.second) }
            .first
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        var user = FirebaseAuth.getInstance().currentUser
        if(user!!.email == c.ADMIN){
            menu.findItem(R.id.costos).isVisible = true
            menu.findItem(R.id.sensores).isVisible = true
            menu.findItem(R.id.empresas).isVisible = true
        }
        else if(user != null){
            menu.findItem(R.id.costos).isVisible = true
            menu.findItem(R.id.sensores).isVisible = false
            menu.findItem(R.id.empresas).isVisible = false
        }else {
            menu.findItem(R.id.costos).isVisible = false
            menu.findItem(R.id.sensores).isVisible = false
            menu.findItem(R.id.empresas).isVisible = false
        }
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
