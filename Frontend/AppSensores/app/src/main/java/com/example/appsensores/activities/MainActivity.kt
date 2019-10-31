package com.example.appsensores.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.appsensores.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //var list_of_items = arrayOf("Agua", "Gas", "Electricidad");
    //var spinner: Spinner? = null;
    //var textView_msg: TextView? = null;
    //var tipo=""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar)

        if(FirebaseAuth.getInstance().currentUser == null){
            intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
            finish()
        }

        agregarProb.setOnClickListener {

        }

        /*
        var prob= findViewById<Button>(R.id.prueba);
        prueba.setOnClickListener {
            intent = Intent(this, CrearSensores::class.java)
            startActivity(intent);
        }
        var agregarProb= findViewById<Button>(R.id.button);
        agregarProb.setOnClickListener {
            var nombre = findViewById<EditText>(R.id.editText2);
            ;
            //Tipo se cambia cuando se selecciona
            //tipo.selec.toString()
            // var tipo = spinner.getSelectedItem().toString()
            //var tipo = findViewById<EditText>(R.id.tipo);
            var unidad = findViewById<EditText>(R.id.editText5);

            intent = Intent(this, VistaSensores::class.java)
            startActivity(intent);
        }



        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        //Button agregarProb = findViewById(R.id.button);
        //Button agregarProb = clearFindViewByIdCache(R.id.button);
*/
    }
/*
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {


        var unit=findViewById<EditText>(R.id.editText5);
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
*/
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

