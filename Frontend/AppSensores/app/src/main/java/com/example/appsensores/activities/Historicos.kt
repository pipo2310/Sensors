package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appsensores.R

import com.softmoore.android.graphlib.Function;
import com.softmoore.android.graphlib.Graph;
import com.softmoore.android.graphlib.GraphView;
import com.softmoore.android.graphlib.Label;
import com.softmoore.android.graphlib.Point;

import android.widget.TextView

import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.appsensores.models.Medicion
import com.example.appsensores.services.MedicionesService
import kotlinx.android.synthetic.main.activity_historicos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Historicos : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    var list_of_items = arrayOf("Agua", "Gas", "Electricidad");
    var list_of_items2 = arrayOf("Ultima semana", "Ultimo mes", "Ultimo año");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historicos)
        setSupportActionBar(toolbar)

        //Spinner de eleccion de tipo de recurso
        spinner2!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner2!!.setAdapter(aa)

       //Spinner de eleccion de tiempo
        spinner3!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aaa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items2)
        // Set layout to use when the list of choices appear
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner3!!.setAdapter(aaa)

        //generarHistoricosAno()
        //generarHistoricosMes()
        //generarHistoricosSemana()
        generarHistoricosDefault()


    }

    fun generarHistoricosDefault() {

        val points = arrayOf(
            Point(1.0, 0.0),
            Point(2.0, 0.0),
            Point(3.0, 0.0),
            Point(4.0, 0.0),
            Point(5.0, 0.0),
            Point(6.0, 0.0),
            Point(7.0, 0.0),
            Point(8.0, 0.0),
            Point(9.0, 0.0),
            Point(10.0, 0.0),
            Point(11.0, 0.0),
            Point(12.0, 0.0)
        )
        val xLabels = arrayOf(
            Label(1.0, "E"),
            Label(2.0, "F"),
            Label(3.0, "M"),
            Label(4.0, "A"),
            Label(5.0, "M"),
            Label(6.0, "J"),
            Label(7.0, "J"),
            Label(8.0, "A"),
            Label(9.0, "S"),
            Label(10.0, "O"),
            Label(11.0, "N"),
            Label(12.0, "D")
        )
        val graph = Graph.Builder()
            .setWorldCoordinates(-2.0, 13.0, 165.0, 191.0)
            .setAxes(0.0, 167.0)
            .setXLabels(xLabels)
            .setYTicks(doubleArrayOf(170.0, 175.0, 180.0, 185.0, 190.0))
            .addFunction({ x -> 170.0 }, Color.GREEN)
            .addLineGraph(points, Color.RED)
            .build()

        val graphView = findViewById<GraphView>(R.id.graph_view)
        graphView.setGraph(graph)
        setTitle("Historicos Anuales")
        val textView = findViewById<TextView>(R.id.graph_view_label)
        textView.setText("Graph of Axes")
    }





    fun generarHistoricosAno() {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val medicionesService: MedicionesService = retrofit.create(MedicionesService::class.java)


        val call: Call<Collection<Medicion>> = medicionesService.getMedicionesAno(3)

        call.enqueue(object: Callback<Collection<Medicion>> {
            override fun onResponse(call: Call<Collection<Medicion>>, response: Response<Collection<Medicion>>) {
                if (!response.isSuccessful())
                {
                    return
                }
                val medicionesAno = response.body()
                llenarGraficoAno(medicionesAno)

            }
            override fun onFailure(call: Call<Collection<Medicion>>, t:Throwable) {

            }
        })

    }

    fun generarHistoricosMes() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val medicionesService: MedicionesService = retrofit.create(MedicionesService::class.java)


        val call: Call<Collection<Medicion>> = medicionesService.getMedicionesMes(3)

        call.enqueue(object: Callback<Collection<Medicion>> {
            override fun onResponse(call: Call<Collection<Medicion>>, response: Response<Collection<Medicion>>) {
                if (!response.isSuccessful())
                {
                    return
                }
                val medicionesMes = response.body()
                llenarGraficoMes(medicionesMes)

            }
            override fun onFailure(call: Call<Collection<Medicion>>, t:Throwable) {

            }
        })


    }

    fun generarHistoricosSemana() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val medicionesService: MedicionesService = retrofit.create(MedicionesService::class.java)


        val call: Call<Collection<Medicion>> = medicionesService.getMedicionesSemana(3)

        call.enqueue(object: Callback<Collection<Medicion>> {
            override fun onResponse(call: Call<Collection<Medicion>>, response: Response<Collection<Medicion>>) {
                if (!response.isSuccessful())
                {
                    return
                }
                val medicionesSemana = response.body()
                llenarGraficoSemana(medicionesSemana)

            }
            override fun onFailure(call: Call<Collection<Medicion>>, t:Throwable) {

            }
        })

    }


    fun llenarGraficoAno(mediciones:Collection<Medicion>) {
        val medicionesAno= arrayOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
        for (medicion in mediciones){

            when(medicion.metrica.split(" ").get(0)){
                "January"-> medicionesAno.set(0,medicion.valor)
                "February"-> medicionesAno.set(1,medicion.valor)
                "March"-> medicionesAno.set(2,medicion.valor)
                "April"-> medicionesAno.set(3,medicion.valor)
                "May"-> medicionesAno.set(4,medicion.valor)
                "June"-> medicionesAno.set(5,medicion.valor)
                "July"-> medicionesAno.set(6,medicion.valor)
                "August"-> medicionesAno.set(7,medicion.valor)
                "September"-> medicionesAno.set(8,medicion.valor)
                "October"-> medicionesAno.set(9,medicion.valor)
                "November"-> medicionesAno.set(10,medicion.valor)
                "December"-> medicionesAno.set(11,medicion.valor)
                else ->{
                    println("No sirve")}
            }

        }


        val points = arrayOf(
            Point(1.0, medicionesAno.get(0)),
            Point(2.0, medicionesAno.get(1)),
            Point(3.0, medicionesAno.get(2)),
            Point(4.0, medicionesAno.get(3)),
            Point(5.0, medicionesAno.get(4)),
            Point(6.0, medicionesAno.get(5)),
            Point(7.0, medicionesAno.get(6)),
            Point(8.0, medicionesAno.get(7)),
            Point(9.0, medicionesAno.get(8)),
            Point(10.0, medicionesAno.get(9)),
            Point(11.0, medicionesAno.get(10)),
            Point(12.0, medicionesAno.get(11))
        )
        val xLabels = arrayOf(
            Label(1.0, "E"),
            Label(2.0, "F"),
            Label(3.0, "M"),
            Label(4.0, "A"),
            Label(5.0, "M"),
            Label(6.0, "J"),
            Label(7.0, "J"),
            Label(8.0, "A"),
            Label(9.0, "S"),
            Label(10.0, "O"),
            Label(11.0, "N"),
            Label(12.0, "D")
        )
        val graph = Graph.Builder()
            .setWorldCoordinates(-2.0, 13.0, -3.0, 20.0)
            .setAxes(0.0, 0.0)
            .setXLabels(xLabels)
            .setYTicks(doubleArrayOf(0.0,5.0, 10.0, 15.0))
            //.addFunction({ x -> 170.0 }, Color.GREEN)
            .addLineGraph(points, Color.RED)
            .build()

        val graphView = findViewById<GraphView>(R.id.graph_view)
        graphView.setGraph(graph)
        setTitle("Historicos Anuales")
        val textView = findViewById<TextView>(R.id.graph_view_label)
        textView.setText("Graph of Axes")
    }

    fun llenarGraficoMes(mediciones:Collection<Medicion>){

        val medicionesMes= arrayOf(0.0,0.0,0.0,0.0)
        for (medicion in mediciones){

            when(medicion.metrica.split(" ").get(0)){
                "1eek"-> medicionesMes.set(0,medicion.valor)
                "2eek"-> medicionesMes.set(1,medicion.valor)
                "3eek"-> medicionesMes.set(2,medicion.valor)
                "4eek"-> medicionesMes.set(3,medicion.valor)

                else ->{
                    println("No sirve")}
            }

        }

        val points = arrayOf(
            Point(1.0, medicionesMes.get(0)),
            Point(4.0, medicionesMes.get(1)),
            Point(7.0, medicionesMes.get(2)),
            Point(10.0, medicionesMes.get(3))
        )
        val xLabels = arrayOf(
            Label(1.0, "S1"),
            Label(4.0, "S2"),
            Label(7.0, "S3"),
            Label(10.0, "S4")
        )
        val graph = Graph.Builder()
            .setWorldCoordinates(-2.0, 11.0, -3.0, 20.0)
            .setAxes(0.0, 0.0)
            .setXLabels(xLabels)
            .setYTicks(doubleArrayOf(0.0,5.0, 10.0, 15.0))
            //.addFunction({ x -> 170.0 }, Color.GREEN)
            .addLineGraph(points, Color.RED)
            .build()

        val graphView = findViewById<GraphView>(R.id.graph_view)
        graphView.setGraph(graph)
        setTitle("Historicos Mensuales")
        val textView = findViewById<TextView>(R.id.graph_view_label)
        textView.setText("Graph of Axes")

    }

    fun llenarGraficoSemana(mediciones:Collection<Medicion>){

        val medicionesSemana= arrayOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0)
        for (medicion in mediciones){

            when(medicion.metrica.split(" ").get(0)){
                "Monday"-> medicionesSemana.set(0,medicion.valor)
                "Tuesday"-> medicionesSemana.set(1,medicion.valor)
                "Wednesday"-> medicionesSemana.set(2,medicion.valor)
                "Thursday"-> medicionesSemana.set(3,medicion.valor)
                "Friday"-> medicionesSemana.set(4,medicion.valor)
                "Saturday"-> medicionesSemana.set(5,medicion.valor)
                "Sunday"-> medicionesSemana.set(6,medicion.valor)

                else ->{
                    println("No sirve")}
            }

        }


        val points = arrayOf(

            Point(1.0, medicionesSemana.get(0)),
            Point(3.0, medicionesSemana.get(1)),
            Point(5.0, medicionesSemana.get(2)),
            Point(7.0, medicionesSemana.get(3)),
            Point(9.0, medicionesSemana.get(4)),
            Point(11.0, medicionesSemana.get(5)),
            Point(13.0, medicionesSemana.get(6))
        )
        val xLabels = arrayOf(
            Label(1.0, "L"),
            Label(3.0, "K"),
            Label(5.0, "M"),
            Label(7.0, "J"),
            Label(9.0, "V"),
            Label(11.0, "S"),
            Label(13.0, "D")
        )
        val graph = Graph.Builder()
            .setWorldCoordinates(-2.0, 14.0, -3.0, 20.0)
            .setAxes(0.0, 0.0)
            .setXLabels(xLabels)
            .setYTicks(doubleArrayOf(0.0,5.0, 10.0, 15.0))
            //.addFunction({ x -> 2.0 }, Color.GREEN)
            .addLineGraph(points, Color.RED)
            .build()

        val graphView = findViewById<GraphView>(R.id.graph_view)
        graphView.setGraph(graph)
        setTitle("Historicos Semanales")
        val textView = findViewById<TextView>(R.id.graph_view_label)
        textView.setText("Graph of Axes")

    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

       if (arg0!!.id == R.id.spinner3) {
            if (position==0){
               generarHistoricosSemana()
            }else if (position==1){
                generarHistoricosMes()
            }else if (position==2){
                generarHistoricosAno()
            }

        }
        /*
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
        */

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
        }else{
            super.onOptionsItemSelected(item)
        }
        return true
    }
}
