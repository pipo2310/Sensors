package com.example.appsensores.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.appsensores.*
import com.example.appsensores.R
import com.example.appsensores.models.Sensor
import com.example.appsensores.services.SensoresService
import com.example.appsensores.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_crear_sensores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CrearSensores : AppCompatActivity(),AdapterView.OnItemSelectedListener  {
    var list_of_items = arrayOf("Agua", "Gas", "Electricidad");
    //var spinner: Spinner? = null;
    //var textView_msg: TextView? = null;
    var tipo=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sensores);
        setSupportActionBar(toolbar)
        var agregarProb= findViewById<Button>(R.id.button);
        agregarProb.setOnClickListener {
            //var nombre = findViewById<EditText>(R.id.editText2);
            ;
            //Tipo se cambia cuando se selecciona
            //tipo.selec.toString()
            // var tipo = spinner.getSelectedItem().toString()
            //var tipo = findViewById<EditText>(R.id.tipo);
            //var unidad = findViewById<EditText>(R.id.editText5);

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://ec2-34-235-147-100.compute-1.amazonaws.com/sensores/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

           // val jsonPlaceHolderApi: SensoresService = retrofit.create(SensoresService::class.java)

            val sensoresService: SensoresService =
                ServiceBuilder.buildService(
                    SensoresService::class.java
                )
            val sensorAAGregar= Sensor()
            val nombreAdd= findViewById<EditText>(R.id.nombreSensor);
            val unidadAdd= findViewById<EditText>(R.id.unidad);
            val tipoAdd=findViewById<Spinner>(R.id.spinner);
            val type:String=tipoAdd.selectedItem.toString()
            if (type=="Agua"){
                sensorAAGregar.tipo=3
            }else if (type=="Electricidad"){
                sensorAAGregar.tipo=2

            }else if (type=="Gas"){
                sensorAAGregar.tipo=1
            }



            sensorAAGregar.id_cuenta=1
            //sensorAAGregar.isAlerta_amarilla=25.0
            //sensorAAGregar.isAlerta_roja=100.0
            sensorAAGregar.nombre=nombreAdd.text.toString()
            sensorAAGregar.unidad=unidadAdd.text.toString()
            intent = Intent(this, VistaSensores::class.java)

            //val call: Call<Sensor> = jsonPlaceHolderApi.crearSensor(Sensor("Sensor Gas 44",1,"mg/m3",1,25.0,50.0))
            val call: Call<Sensor> = sensoresService.crearSensor(sensorAAGregar)

            call.enqueue(object:Callback<Sensor> {
                override fun onResponse(call: Call<Sensor>, response: Response<Sensor>) {
                    if (response.isSuccessful()) {
                        finish()
                        startActivity(intent);
                        Toast.makeText(this@CrearSensores,
                            "Sensor creado exitosamente",Toast.LENGTH_SHORT).show()
                        return
                    }else{
                        Toast.makeText(this@CrearSensores,
                            "No se pudo crear el sensor",Toast.LENGTH_SHORT).show()
                    }
                    Log.e("--------------Siuuuu", "Bien");
                }
                override fun onFailure(call: Call<Sensor>?, t: Throwable?) {
                    Toast.makeText(this@CrearSensores,
                        "No se pudo crear el sensor",Toast.LENGTH_SHORT).show()
                    Log.e("###########Error", "Unable to submit post to API.");
                }
            })



        }



        //spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
      //  val adapt = ArrayAdapter.createFromResource(this, R.array.context_names , R.layout.spinner_recursos)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        //Button agregarProb = findViewById(R.id.button);
        //Button agregarProb = clearFindViewByIdCache(R.id.button);

    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        var unit=findViewById<EditText>(R.id.unidad);
        if (position==0){
            unit.setText("mL/s")
            tipo="mL/s"
        }else if (position==1){
            unit.setText("mg/m3")
            tipo="mg/m3"
        }else if (position==2){
            unit.setText("Ws")
            tipo="Ws"
        }
        //Con position se sabe cual selecciono
        //if es 0 es litros si es 1 watts y si 2 es lo que sea que se mide el gas jeje
        //textView_msg!!.text = "Selected : "+list_of_items[position]
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

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
