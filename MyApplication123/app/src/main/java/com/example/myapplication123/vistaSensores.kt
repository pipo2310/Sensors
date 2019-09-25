package com.example.myapplication123

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ActionBar
import android.widget.TableLayout
import android.util.Log
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup
import android.view.View
import android.widget.Button
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_main.view.*
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_vista_sensores.*

class vistaSensores : AppCompatActivity() {
    lateinit var t1: TableLayout

    //var sensores

    val tableLayout by lazy{ TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_sensores)
        t1 = TableLayout(this)

        /* Llenar vector de sensores
        * */
        val filas = 3 // Obtener count de sensores

        val nomSensores = arrayOf("SENSOR AGUA 1", "SENSOR AGUA 2", "SENSOR ELEC 1")


        val lp = TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        tableLayout.apply {
            layoutParams = lp
            isShrinkAllColumns = true
        }


        createTable(filas, nomSensores)


    }

    fun createTable(rows: Int , sensores:Array<String>){
        for (i in 0 until rows) {

            val row = TableRow(this)
            row.gravity = Gravity.CENTER
            row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)



            val button = Button(this)
            button.apply {
                layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT)
                text = "Sensor:  ${sensores.get(i)}  "
            }
            row.addView(button)



            tableLayout.addView(row)
        }
        linearLayout.addView(tableLayout)

    }


}
