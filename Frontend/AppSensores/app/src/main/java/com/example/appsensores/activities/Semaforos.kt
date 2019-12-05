package com.example.appsensores.activities

import android.app.*
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.core.app.NotificationCompat
import com.example.appsensores.R
import com.example.appsensores.models.Cuenta
import com.example.appsensores.models.ValorSemaforo
import com.example.appsensores.services.CuentasService
import com.example.appsensores.services.ServiceBuilder
import com.example.appsensores.services.ValorSemaforoService
import com.example.appsensores.utilities.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_semaforos.*
import kotlinx.android.synthetic.main.activity_vista_sensores.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Semaforos : AppCompatActivity() {
    private val c: Constants = Constants()
    // Variables para notificaciones
    private var CHANNEL_ID = "personal_notification"
    private var NOTIFICATION_ID = 1

    private var progressStatus = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semaforos)
        toolbar.title = "Consumo de Recursos"
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        // Se obtiene el usuario
        var user = FirebaseAuth.getInstance().currentUser
        var empresaId = "wal@gmail.com"
        if (user != null) {
            //empresaId = user.email.orEmpty()
        } else {
            //empresaId = "a" // codigoEmpresa //
        }

        intent = Intent(this, MainActivity::class.java)

        val cuentasService: CuentasService = ServiceBuilder.buildService(
            CuentasService::class.java
        )

        val call: Call<Cuenta> = cuentasService.getCuenta2(empresaId)

        call.enqueue(object : Callback<Cuenta> {
            override fun onResponse(call: Call<Cuenta>, response: Response<Cuenta>) {
                if (!response.isSuccessful()) {
                    Log.println(Log.INFO, "ERROR", "adios")
                    Snackbar.make(
                        vista_semaforos,
                        "Ocurrio un error, intente luego",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return
                }
                val cuenta = response.body()
                if(cuenta != null) {
                    crearSemaforos(cuenta)
                }else{
                    Log.println(Log.INFO, "ERROR", "adios")
                    Snackbar.make(
                        vista_semaforos,
                        "Ocurrio un error, intente luego",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    startActivity(intent)
                }
            }
            override fun onFailure(call: Call<Cuenta>, t: Throwable) {
                Log.println(Log.INFO, "ERROR", "adios")
                Snackbar.make(
                    vista_semaforos,
                    "Ocurrio un error, intente luego",
                    Snackbar.LENGTH_SHORT
                ).show()
                startActivity(intent)
            }
        })
    }

    fun crearSemaforos(cuenta: Cuenta){
        for(i in 0 until 3){
            if(i==c.TIPO_AGUA){
                val pb = findViewById<ProgressBar>(R.id.bar_agua)
                val totalLimit1 = 500
                val tv1 = findViewById<TextView>(R.id.displayGas)
                llamarABackend(pb, totalLimit1, i, cuenta, tv1)
            }
            else if(i==c.TIPO_GAS){
                val pb = findViewById<ProgressBar>(R.id.bar_gas)
                val totalLimit2 = 500
                val tv2 = findViewById<TextView>(R.id.displayGas)
                llamarABackend(pb, totalLimit2, i, cuenta, tv2)
            }
            else if(i==c.TIPO_ELECT){
                val pb = findViewById<ProgressBar>(R.id.bar_elect)
                val totalLimit3 = 500
                val tv3 = findViewById<TextView>(R.id.displayElect)
                llamarABackend(pb, totalLimit3, i, cuenta, tv3)
            }
        }
        val btn2 = findViewById<Button>(R.id.btn_modi)
        btn2.setOnClickListener {
            intent = Intent(this, ModificarLimites::class.java)
            startActivity(intent)
            //finish()
        }
    }

    fun llamarABackend(pb: ProgressBar?, totalLimit: Int, idSem: Int, cuenta: Cuenta, displayStatus: TextView){
        val thread = Thread {
            pb!!.max = totalLimit
            while (true) {
                val valorSemaforoService: ValorSemaforoService = ServiceBuilder.buildService(
                    ValorSemaforoService::class.java
                )

                val call: Call<List<ValorSemaforo>> = valorSemaforoService.getValorSemaforo(idSem)

                call.enqueue(object : Callback<List<ValorSemaforo>> {
                    override fun onResponse(call: Call<List<ValorSemaforo>>, response: Response<List<ValorSemaforo>>) {
                        if (!response.isSuccessful()) {
                            Snackbar.make(
                                vista_semaforos,
                                "Ocurrio un error, intente luego",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            return
                        }
                        val valorSemaforo = response.body()
                        aumentarBarra(pb, idSem, cuenta, valorSemaforo, displayStatus)
                    }

                    override fun onFailure(call: Call<List<ValorSemaforo>>, t: Throwable) {
                        Snackbar.make(
                            vista_semaforos,
                            "Ocurrio un error, intente luego",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        startActivity(intent)
                    }
                })

                try {
                    Thread.sleep(6000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }

    fun aumentarBarra(pb: ProgressBar?, idSem: Int, cuenta: Cuenta, valorSemaforo: List<ValorSemaforo>, displayStatus: TextView) {
        var yellowLimit: Int
        var redLimit: Int
        var tv = findViewById<TextView>(R.id.titulo)
        val res = resources
        if (idSem == 0){
            yellowLimit = cuenta.limiteAguaMedio.toInt()
            redLimit = cuenta.limiteAguaAlto.toInt()
        }else if (idSem == 1){
            yellowLimit = cuenta.limiteGasMedio.toInt()
            redLimit = cuenta.limiteGasAlto.toInt()
        }else{
            yellowLimit = cuenta.limiteElectMedio.toInt()
            redLimit = cuenta.limiteElectAlto.toInt()
        }

        // Falta actualizar el progress bar con datos del sensor
        progressStatus = valorSemaforo[0].valor.toInt()

        displayStatus.text = ""+progressStatus

        handler.post {
            pb!!.progress = progressStatus
            if (progressStatus >= yellowLimit) {

                pb.setProgressDrawable(res.getDrawable(R.drawable.yellowprogressbar))
            }
            if (progressStatus >= redLimit) {

                pb.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar))
                showNotification(idSem)

            }
        }
    }



    // Notificar de alertas
    private fun showNotification(idSem : Int) {
        var mensaje : String = ""
        if(idSem == 0){ // agua
            mensaje = "El sensor de agua sobrepasa los límites"
        }else if(idSem == 1){ // gas
            mensaje = "El sensor de gas sobrepasa los límites"
        }else if(idSem == 2){ // electricidad
            mensaje = "El sensor de electricidad sobrepasa los límites"
        }
        val resultIntent = Intent(this, Semaforos::class.java)
// Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(this, "default")
            .setSmallIcon(R.drawable.ic_alerta_sensor)
            .setContentTitle("ALERTA EN SENSOR")
            .setContentText(mensaje)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

            .setContentIntent(resultPendingIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            builder.setChannelId("com.example.appsensores")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(
                "com.example.appsensores",
                "app",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            if (notificationManager != null)
            {
                notificationManager.createNotificationChannel(channel)
            }
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build())
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