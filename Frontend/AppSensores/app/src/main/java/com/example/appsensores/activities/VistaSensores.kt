package com.example.appsensores.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.widget.*
import kotlinx.android.synthetic.main.activity_vista_sensores.*
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Typeface
import android.view.*
import kotlinx.android.synthetic.main.activity_vista_sensores.toolbar
import kotlinx.android.synthetic.main.another_view.view.*
import com.android.volley.RequestQueue
import com.example.appsensores.R
import com.example.appsensores.models.Sensor
import com.example.appsensores.services.SensoresService
import com.example.appsensores.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class VistaSensores : AppCompatActivity() {
    lateinit var t1: TableLayout


    //lateinit var mqueue:RequestQueue
    val tableLayout by lazy{ TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_sensores)
        setSupportActionBar(toolbar)
        //recuperarSensores()

        var agregarSensor = findViewById<Button>(R.id.button2);
        agregarSensor.setOnClickListener {
            intent = Intent(this, CrearSensores::class.java)
            startActivity(intent);
        }


    }

    override fun onResume() {
        super.onResume()
        recuperarSensores()

    }

    override fun onStart() {
        super.onStart()


    }

    fun recuperarSensores() {

        t1 = TableLayout(this)

        //recuperarSensores()

        val lp = TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        tableLayout.apply {
            layoutParams = lp
            isShrinkAllColumns = true
        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val sensoresService: SensoresService = retrofit.create(SensoresService::class.java)

        val call: Call<List<Sensor>> = sensoresService.getSensores()

        call.enqueue(object:Callback<List<Sensor>> {
            override fun onResponse(call:Call<List<Sensor>>, response: Response<List<Sensor>>) {
                if (!response.isSuccessful())
                {
                    return
                }
                val sensors = response.body()
                createTable(sensors)
            }
            override fun onFailure(call:Call<List<Sensor>>, t:Throwable) {

            }
        })

    }

    fun createTable(sensores:List<Sensor>){
        for (sensor in sensores) {

            val row = TableRow(this)
            row.gravity = Gravity.CENTER
            row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)


            val frame = FrameLayout(this)
            val divisionFrame=FrameLayout(this)
            val eliminar =ImageButton(this)
            val actualizar = ImageButton(this)
            val nombreSensor = TextView(this)




            nombreSensor.text="" + sensor.nombre
            nombreSensor.setPadding(50,0,0,0)
            nombreSensor.gravity= Gravity.LEFT or Gravity.CENTER_VERTICAL
            nombreSensor.textSize=18f
            nombreSensor.setTextColor(Color.parseColor("#FFFFFF"))
            nombreSensor.setTypeface(null, Typeface.BOLD)
            frame.setBackgroundColor(Color.parseColor("#3B77D2"))

            val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(650,10,10,10)
            params.gravity=Gravity.CENTER_VERTICAL

            val params2 = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            params2.setMargins(750,10,10,10)
            params2.gravity=Gravity.CENTER_VERTICAL
            eliminar.setLayoutParams(params2)
            divisionFrame.setBackgroundColor(Color.parseColor("#7BD451"))

            val id:Long=sensor.getSensoresPk()
            eliminar.setBackgroundColor(Color.parseColor("#00ff0000"))
            eliminar.setOnClickListener {
                accionDeEliminar(sensor.sensoresPk)
            }


// Changes the height and width to the specified *pixels*
            divisionFrame.apply {layoutParams = TableRow.LayoutParams(850,
                5)}

            frame.apply {layoutParams = TableRow.LayoutParams(850,
                150)}

            eliminar.setImageResource(R.drawable.delete32)

            actualizar.setLayoutParams(params)
            actualizar.setBackgroundColor(Color.parseColor("#00ff0000"))
            actualizar.setBackgroundResource(R.drawable.edit32)
            actualizar.setOnClickListener {
                accionDeActualizar(sensor.nombre,sensor.tipo,sensor.unidad,sensor.sensoresPk,sensor.id_cuenta)
            }

            frame.addView(nombreSensor)
            frame.addView(eliminar)
            frame.addView(actualizar)
            frame.addView(divisionFrame)
            row.addView(frame)

            tableLayout.addView(row)
        }
        linearLayout.addView(tableLayout)

    }

    fun accionDeActualizar(name:String,type:Int,unit:String,sensorPk:Long,idcuenta:Long) {

        intent = Intent(this, ModificarSensores::class.java)
        intent.putExtra("nombre",name)
        intent.putExtra("id",sensorPk)
        intent.putExtra("tipo",type)
        intent.putExtra("unidad",unit)
        intent.putExtra("id_cuenta",idcuenta)


        startActivity(intent);


    }

    fun accionDeEliminar(id: Long) {

        val mDialog = LayoutInflater.from(this).inflate(R.layout.another_view, null);
        mDialog.deleteText.setText("¿Está seguro que desea eliminar el sensor "+id)

        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialog)
            .setTitle("Alerta")


        val mAlertDialog = mBuilder.show()

        mDialog.button_cancela.setOnClickListener{
            mAlertDialog.dismiss()
        }

        mDialog.button_elimina.setOnClickListener {
            val sensoresService: SensoresService = ServiceBuilder.buildService(
                SensoresService::class.java)
            val requestCall: Call<Int> = sensoresService.borrarSensores(id)
            requestCall.enqueue(object: Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful()){
                        Toast.makeText(this@VistaSensores,
                            "Sensor eliminado exitosamente",Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(getIntent())

                }else{
                        Toast.makeText(this@VistaSensores,
                            "No se pudo eliminar",Toast.LENGTH_SHORT).show()


                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Toast.makeText(this@VistaSensores,
                        "No se pudo eliminar",Toast.LENGTH_SHORT).show()

                }
            }


            )

            mAlertDialog.dismiss()

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
        }else if(item.itemId == R.id.historicos){
            intent = Intent(this, Historicos::class.java)
            startActivity(intent)
        }
        else{
            super.onOptionsItemSelected(item)
        }
        return true
    }


}
