package com.example.appsensores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ActionBar
import android.graphics.Color
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_vista_sensores.*
import android.R.attr.gravity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Typeface
import android.view.*
import android.widget.LinearLayout
import androidx.core.view.setMargins
import com.example.appsensores.R
import com.example.appsensores.Semaforos
import kotlinx.android.synthetic.main.activity_crear_sensores.*
import kotlinx.android.synthetic.main.activity_vista_sensores.toolbar
import kotlinx.android.synthetic.main.another_view.view.*
import android.graphics.drawable.Drawable





class VistaSensores : AppCompatActivity() {
    lateinit var t1: TableLayout



    val tableLayout by lazy{ TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_sensores)
        setSupportActionBar(toolbar)
        t1 = TableLayout(this)

        /* Llenar vector de sensores
        * */
        val filas = 3// Obtener count de sensores

        val nomSensores = arrayOf("SENSOR AGUA 1", "SENSOR AGUA 2", "SENSOR ELEC 1")


        val lp = TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        tableLayout.apply {
            layoutParams = lp
            isShrinkAllColumns = true
        }


        createTable(filas, nomSensores)


        var agregarSensor = findViewById<Button>(R.id.button2);
        agregarSensor.setOnClickListener {
            intent = Intent(this, CrearSensores::class.java)
            startActivity(intent);
        }


    }

    fun createTable(rows: Int , sensores:Array<String>){
        for (i in 0 until rows) {

            val row = TableRow(this)
            row.gravity = Gravity.CENTER
            row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)


            val frame = FrameLayout(this)
            val divisionFrame=FrameLayout(this)
            val eliminar =ImageButton(this)
            val actualizar = ImageButton(this)
            val nombreSensor = TextView(this)
            val button = Button(this)
            nombreSensor.text="${sensores.get(i)}"
            nombreSensor.setPadding(50,0,0,0)
            nombreSensor.gravity= Gravity.LEFT or Gravity.CENTER_VERTICAL
            nombreSensor.textSize=18f
            nombreSensor.setTextColor(Color.parseColor("#FFFFFF"))
            nombreSensor.setTypeface(null, Typeface.BOLD)
            frame.setBackgroundColor(Color.parseColor("#3B77D2"))
            //frame.foregroundGravity= Gravity.RIGHT

            val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            //params.gravity = Gravity.TOP or Gravity.RIGHT
            params.setMargins(650,10,10,10)
            params.gravity=Gravity.CENTER_VERTICAL

            val params2 = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            //params.gravity = Gravity.TOP or Gravity.RIGHT
            params2.setMargins(750,10,10,10)
            params2.gravity=Gravity.CENTER_VERTICAL
            eliminar.setLayoutParams(params2)
            divisionFrame.setBackgroundColor(Color.parseColor("#7BD451"))
            eliminar.id=i
            eliminar.setBackgroundColor(Color.parseColor("#00ff0000"))
            eliminar.setOnClickListener {
                accionDeEliminar(eliminar.id,"${sensores.get(i)}")
            }

            // frame.set

// Changes the height and width to the specified *pixels*
            divisionFrame.apply {layoutParams = TableRow.LayoutParams(850,
                5)}

            frame.apply {layoutParams = TableRow.LayoutParams(850,
                150)}
            //eliminar.background=@color/fondo
            //val drawable = resources.getDrawable(R.drawable.deleteicon)
            eliminar.setImageResource(R.drawable.delete32)
            //eliminar.setImageDrawable(drawable)
            // eliminar.apply {layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            //   TableRow.LayoutParams.WRAP_CONTENT)
            //  }
            //actualizar.setImageResource(R.drawable.editbutton)

            actualizar.setLayoutParams(params)
            actualizar.id=i*1000
            //actualizar.apply {layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            //    TableRow.LayoutParams.WRAP_CONTENT)
             //   id=i*1000
           // }
            actualizar.setBackgroundColor(Color.parseColor("#00ff0000"))
            actualizar.setBackgroundResource(R.drawable.edit32)


            actualizar.setOnClickListener {
                accionDeActualizar("Sensor:  ${sensores.get(i)}  ")
            }

            button.apply {
                layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT)
                text = "Sensor:  ${sensores.get(i)}  "
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

    fun accionDeActualizar(s: String) {
        intent = Intent(this, ModificarSensores::class.java)
        intent.putExtra("nombre",s)
        //pasar los 3 elementos del objeto como extras separados y recuperarlos del otro lado con el "" que sea pertinente
        startActivity(intent);

    }

    fun accionDeEliminar(id: Int, text: String) {

        val mDialog = LayoutInflater.from(this).inflate(R.layout.another_view, null);
        mDialog.deleteText.setText("¿Está seguro que desea eliminar $text ?")

        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialog)
            .setTitle("Alerta")


        val mAlertDialog = mBuilder.show()

        mDialog.button_cancela.setOnClickListener{
            mAlertDialog.dismiss()
        }

        mDialog.button_elimina.setOnClickListener {
            //parametro para eliminar: text
            //Deberia hacer refresh de la pagina y poner que se elimino exitosamente
            //mAlertDialog.dismiss()
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
            startActivity(intent)

        }else{
            super.onOptionsItemSelected(item)
        }
        return true
    }


}
