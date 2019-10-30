package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.EditText
import com.example.appsensores.R
import com.example.appsensores.models.Medicion
import com.example.appsensores.models.TipoSensor
import com.example.appsensores.services.MedicionesService
import com.example.appsensores.services.TiposSensorService
import kotlinx.android.synthetic.main.activity_vista_sensores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Costos : AppCompatActivity() {

    var tipos_sensores = emptyList<TipoSensor>()
    var costo_agua = 0.0F
    var costo_elec = 0.0F
    var costo_gas = 0.0F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.appsensores.R.layout.activity_costos)
        setSupportActionBar(toolbar)

        intent = Intent(this, Costos::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

        getData()
    }

    fun sendFeedback(button: View) {
        val costo_aguaField = findViewById(com.example.appsensores.R.id.costo_agua) as EditText
        costo_agua = costo_aguaField.text.toString().toFloat()
        val costo_elecField = findViewById(com.example.appsensores.R.id.costo_elec) as EditText
        costo_elec = costo_elecField.text.toString().toFloat()
        val costo_gasField = findViewById(com.example.appsensores.R.id.costo_gas) as EditText
        costo_gas = costo_gasField.text.toString().toFloat()

        putData()

        

    }

    private fun getData(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
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
                costo_agua = tipos_sensores[2].costo
                costo_gas = tipos_sensores[0].costo
                costo_elec = tipos_sensores[1].costo
                findViewById<EditText>(R.id.costo_agua).setText(costo_agua.toString())
                findViewById<EditText>(R.id.costo_elec).setText(costo_elec.toString())
                findViewById<EditText>(R.id.costo_gas).setText(costo_gas.toString())


            }

            override fun onFailure(call: Call<List<TipoSensor>>?, t: Throwable?) {

            }
        })

    }

    private fun putData(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tiposensorService: TiposSensorService = retrofit.create(TiposSensorService::class.java)

        val call: Call<List<TipoSensor>> = tiposensorService.putTiposSensor()

        call.enqueue(object: Callback<List<TipoSensor>> {
            override fun onResponse(call: Call<List<TipoSensor>>, response: Response<List<TipoSensor>>) {
                if (!response.isSuccessful())
                {
                    return
                }

            }

            override fun onFailure(call: Call<List<TipoSensor>>?, t: Throwable?) {

            }
        })

    }


}
