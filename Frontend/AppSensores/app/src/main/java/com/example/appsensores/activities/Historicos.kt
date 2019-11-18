package com.example.appsensores.activities

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appsensores.R

import com.softmoore.android.graphlib.Function;
import com.softmoore.android.graphlib.Graph;
import com.softmoore.android.graphlib.GraphView;
import com.softmoore.android.graphlib.Label;
import com.softmoore.android.graphlib.Point;

import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.os.StrictMode
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.appsensores.models.Medicion
import com.example.appsensores.services.MedicionesService
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.activity_historicos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.util.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.io.FileOutputStream
import java.net.URI
import java.util.Date
import kotlin.collections.ArrayList


class Historicos : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    var list_of_items = arrayOf("Agua", "Gas", "Electricidad");
    var list_of_items2 = arrayOf("Ultima semana", "Ultimo mes", "Ultimo año");
    private var btnSS: Button? = null
    private var btnshare: Button? = null
    private var btnPL: Button? = null
    private var iv: ImageView? = null
    private var sharePath = "no"

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

        val btnHHH = findViewById<Button>(R.id.button2)
        btnHHH.setOnClickListener {
            generarReportes()
        }

        //generarHistoricosAno()
        //generarHistoricosMes()
        //generarHistoricosSemana()
        //var agregarSensor = findViewById<Button>(R.id.button2);
        //agregarSensor.setOnClickListener {
        //    takeScreenshot()
        //}

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        requestReadPermissions()

        btnSS = findViewById(R.id.btnSS)
        btnshare = findViewById(R.id.btnShare)
        btnPL = findViewById(R.id.btnPL)

        iv = findViewById(R.id.iv)

        btnSS!!.setOnClickListener { takeScreenshot("sss") }

        btnshare!!.setOnClickListener {
            if (sharePath != "no") {
                share(sharePath)
            }
        }

        btnPL!!.setOnClickListener {
            //val intent = Intent(this@MainActivity, ParticularLayoutActivity::class.java)
            //startActivity(intent)
        }
        generarHistoricosDefault()


    }

    private fun takeScreenshot(nombreArchivo:String) {
        val now = Date()
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)

        try {
            // image naming and path  to include sd card  appending name you choose for file
            val mPath = Environment.getExternalStorageDirectory().toString() + "/"  +nombreArchivo+ ".jpg"

            // create bitmap screen capture
            val v1 = window.decorView.rootView
            v1.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(v1.drawingCache)
            v1.isDrawingCacheEnabled = false

            val imageFile = File(mPath)

            val outputStream = FileOutputStream(imageFile)
            val quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.flush()
            outputStream.close()

            //setting screenshot in imageview
            val filePath = imageFile.path

            val ssbitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            //iv!!.setImageBitmap(ssbitmap)
            sharePath = filePath

        } catch (e: Throwable) {
            // Several error may come out with file handling or DOM
            e.printStackTrace()
        }

    }

    private fun share(sharePath: String) {

        val file = File("/storage/emulated/0/histAnnoAgua.jpg")
        val uri = Uri.fromFile(file)
        val file2 = File("/storage/emulated/0/histMesAgua.jpg")
        val uri2 = Uri.fromFile(file2)
        val file3 = File("/storage/emulated/0/histSemanaAgua.jpg")
        val uri3 = Uri.fromFile(file3)
        val intent = Intent()
        intent.setAction(Intent.ACTION_SEND_MULTIPLE)
        intent.type = "image/jpg"
        var uris=ArrayList<Uri>()
        uris.add(uri)
        uris.add(uri2)
        uris.add(uri3)
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,uris)
        startActivity(intent)

    }

    private fun requestReadPermissions() {

        Dexter.withActivity(this)
            .withPermissions( Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        Toast.makeText(applicationContext, "All permissions are granted by user!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // show alert dialog navigating to Settings
                        //openSettingsDialog();
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener(object : PermissionRequestErrorListener {
                override fun onError(error: DexterError) {
                    Toast.makeText(applicationContext, "Some Error! ", Toast.LENGTH_SHORT).show()
                }
            })
            .onSameThread()
            .check()
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





    fun generarHistoricosAno(generarPDF:Boolean) {

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
                llenarGraficoAno(medicionesAno,generarPDF)

            }
            override fun onFailure(call: Call<Collection<Medicion>>, t:Throwable) {

            }
        })

    }

    fun generarHistoricosMes(generarPDF:Boolean) {
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
                llenarGraficoMes(medicionesMes,generarPDF)

            }
            override fun onFailure(call: Call<Collection<Medicion>>, t:Throwable) {

            }
        })


    }

    fun generarHistoricosSemana(generarPDF:Boolean) {
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
                llenarGraficoSemana(medicionesSemana,generarPDF)

            }
            override fun onFailure(call: Call<Collection<Medicion>>, t:Throwable) {

            }
        })

    }


    fun llenarGraficoAno(mediciones:Collection<Medicion>,generarPDF:Boolean) {
        val medicionesAno= arrayOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
        val annos= arrayOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
        for (medicion in mediciones){

            when(medicion.metrica.split(" ").get(0)){
                "January"-> {
                    medicionesAno.set(0,medicion.valor)
                    annos.set(0,medicion.anno)
                }
                "February"-> {
                    medicionesAno.set(1,medicion.valor)
                    annos.set(1,medicion.anno)
                }
                "March"-> {
                    medicionesAno.set(2,medicion.valor)
                    annos.set(2,medicion.anno)}
                "April"-> {
                    medicionesAno.set(3,medicion.valor)
                    annos.set(3,medicion.anno)}
                "May"-> {
                    medicionesAno.set(4,medicion.valor)
                    annos.set(4,medicion.anno)}
                "June"-> {
                    medicionesAno.set(5,medicion.valor)
                    annos.set(5,medicion.anno)}
                "July"-> {
                    medicionesAno.set(6,medicion.valor)
                    annos.set(6,medicion.anno)}
                "August"->{
                    medicionesAno.set(7,medicion.valor)
                    annos.set(7,medicion.anno)}
                "September"-> {
                    medicionesAno.set(8,medicion.valor)
                    annos.set(8,medicion.anno)}
                "October"->{
                    medicionesAno.set(9,medicion.valor)
                    annos.set(9,medicion.anno)}
                "November"-> {
                    medicionesAno.set(10,medicion.valor)
                    annos.set(10,medicion.anno)}
                "December"->{
                    medicionesAno.set(11,medicion.valor)
                    annos.set(11,medicion.anno)}
                else ->{
                    println("No sirve")}
            }


        }

        val listaDeMeses = arrayOf("E","F","M","A","M","J","J","A","S","O","N","D")
        var contador = ( Calendar.getInstance().get(Calendar.MONTH) ) + 1
        var i = 0

        var points = arrayListOf<Point>()
        var xLabels = arrayListOf<Label>()

        while(i < 12){

            if(contador == 12){
                contador = 0
            }

            points.add( Point((i+1).toDouble(), medicionesAno.get(contador)) )
            xLabels.add( Label ( (i+1).toDouble(), listaDeMeses.get(contador) ) )

            contador++
            i++
        }


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
        if(generarPDF){
            takeScreenshot("histAnnoAgua")
        }

    }

    fun llenarGraficoMes(mediciones:Collection<Medicion>,generarPDF:Boolean){

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
        if(generarPDF){
            takeScreenshot("histMesAgua")
        }

    }

    fun llenarGraficoSemana(mediciones:Collection<Medicion>,generarPDF:Boolean){

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

        val listaDeDias = arrayOf("L","K","M","J","V","S","D")
        var contador = ( Calendar.getInstance().get(Calendar.DAY_OF_WEEK) ) - 1
        var i = 0
        var ticks = 1

        var pointsDays = arrayListOf<Point>()
        var xLabelsDays = arrayListOf<Label>()

        while(i < 7){

            if(contador == 7){
                contador = 0
            }

            pointsDays.add( Point((ticks).toDouble(), medicionesSemana.get(contador)) )
            xLabelsDays.add( Label ( (ticks).toDouble(), listaDeDias.get(contador) ) )

            contador++
            i++
            ticks+=2
        }



        val graph = Graph.Builder()
            .setWorldCoordinates(-2.0, 14.0, -3.0, 20.0)
            .setAxes(0.0, 0.0)
            .setXLabels(xLabelsDays)
            .setYTicks(doubleArrayOf(0.0,5.0, 10.0, 15.0))
            //.addFunction({ x -> 2.0 }, Color.GREEN)
            .addLineGraph(pointsDays, Color.RED)
            .build()

        val graphView = findViewById<GraphView>(R.id.graph_view)
        graphView.setGraph(graph)
        setTitle("Historicos Semanales")
        val textView = findViewById<TextView>(R.id.graph_view_label)
        textView.setText("Graph of Axes")
        if(generarPDF){
            takeScreenshot("histSemanaAgua")
        }

    }
    fun generarReportes(){
        generarHistoricosAno(true)
        //takeScreenshot("histAnnoAgua")
        generarHistoricosMes(true)
        //takeScreenshot("histMesAgua")
        generarHistoricosSemana(true)
        //takeScreenshot("histSemanaAgua")
    }




    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

       if (arg0!!.id == R.id.spinner3) {
            if (position==0){
               generarHistoricosSemana(false)
            }else if (position==1){
                generarHistoricosMes(false)
            }else if (position==2){
                generarHistoricosAno(false)
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
