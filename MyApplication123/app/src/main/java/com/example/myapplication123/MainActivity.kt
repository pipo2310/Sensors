package com.example.myapplication123

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    var list_of_items = arrayOf("Agua", "Gas", "Electricidad");
    //var spinner: Spinner? = null;
    //var textView_msg: TextView? = null;
    var tipo=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        var agregarProb= findViewById<Button>(R.id.button);
        agregarProb.setOnClickListener {
            var nombre = findViewById<EditText>(R.id.editText2);
            ;
            //Tipo se cambia cuando se selecciona
            //tipo.selec.toString()
           // var tipo = spinner.getSelectedItem().toString()
            //var tipo = findViewById<EditText>(R.id.tipo);
            var unidad = findViewById<EditText>(R.id.editText5);

            intent = Intent(this, vistaSensores::class.java)
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

 }

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
}
