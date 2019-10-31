package com.example.appsensores.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.appsensores.R
import kotlinx.android.synthetic.main.activity_vista_sensores.*


class Semaforos : AppCompatActivity() {
    private var progressStatus = 0
    private val handler = Handler()
    private var yellowLimit1 = 45
    private var redLimit1 = 75
    private var yellowLimit2 = 50
    private var redLimit2 = 130
    private var yellowLimit3 = 125
    private var redLimit3 = 275

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semaforos)
        setSupportActionBar(toolbar)
        intent = Intent(this, ModificarLimites::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

        for(i in 0 until 3){
            if(i==0){
                val pb = findViewById<ProgressBar>(R.id.bar_agua)
                val totalLimit1 = 100
                aumentarBarra(pb, yellowLimit1, redLimit1, totalLimit1, i)
            }
            else if(i==1){
                val pb = findViewById<ProgressBar>(R.id.bar_gas)
                val totalLimit2 = 200
                aumentarBarra(pb, yellowLimit2, redLimit2, totalLimit2, i)
            }
            else{
                val pb = findViewById<ProgressBar>(R.id.bar_elect)
                val totalLimit3 = 300
                aumentarBarra(pb, yellowLimit3, redLimit3, totalLimit3, i)
            }
        }
        val btn2 = findViewById<Button>(R.id.btn_modi)
        btn2.setOnClickListener {
            intent.putExtra("yellowlimA", yellowLimit1)
            intent.putExtra("redlimA", redLimit1)
            intent.putExtra("yellowlimG", yellowLimit2)
            intent.putExtra("redlimG", redLimit2)
            intent.putExtra("yellowlimE", yellowLimit3)
            intent.putExtra("redlimE", redLimit3)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        /*
        yellowLimit2 = intent.getIntExtra("yellowlimG", yellowLimit2)
        redLimit2 = intent.getIntExtra("redlimG", redLimit2)
        yellowLimit3 = intent.getIntExtra("yellowlimE", yellowLimit3)
        redLimit3 = intent.getIntExtra("redlimE", redLimit3)
        */
        /*
        intent = Intent(this, ModificarLimites::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        val b = intent.extras
        if(b!!.getInt("yellowlimA") != null){
            yellowLimit1 = b!!.getInt("yellowlimA")
            var text1 = findViewById<TextView>(R.id.titulo)
            text1.text = yellowLimit1.toString()
        }
        yellowLimit1 = intent.getIntExtra("yellowlimA", yellowLimit1)
        var text1 = findViewById<TextView>(R.id.titulo)
        text1.text = yellowLimit1.toString()
        */
    }

    fun verificarLimites(){
        /*
        yellowLimit1 = getIntent().getIntExtra("yellowlimA", yellowLimit1)
        redLimit1 = intent.getIntExtra("redlimA", redLimit1)
        yellowLimit2 = intent.getIntExtra("yellowlimG", yellowLimit2)
        redLimit2 = intent.getIntExtra("redlimG", redLimit2)
        yellowLimit3 = intent.getIntExtra("yellowlimE", yellowLimit3)
        redLimit3 = intent.getIntExtra("redlimE", redLimit3)
        */
    }


    fun aumentarBarra(pb: ProgressBar?, yellowLimitParam: Int, redLimitParam: Int, totalLimit: Int, idSem: Int) {
        var yellowLimit = yellowLimitParam
        var redLimit = redLimitParam
        var tv = findViewById<TextView>(R.id.titulo)
        val res = resources
        val thread = Thread {
            pb!!.max = totalLimit
            while (progressStatus < totalLimit) {
                if (idSem == 0){
                    yellowLimit = intent.getIntExtra("yLA", yellowLimit)
                    redLimit = intent.getIntExtra("rLA", redLimit)
                }else if (idSem == 1){
                    yellowLimit = intent.getIntExtra("yLG", yellowLimit)
                    redLimit = intent.getIntExtra("rLG", redLimit)
                }else{
                    yellowLimit = intent.getIntExtra("yLR", yellowLimit)
                    redLimit = intent.getIntExtra("rLR", redLimit)
                }

                progressStatus += 1

                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                handler.post {
                    pb.progress = progressStatus
                    if (progressStatus >= yellowLimit) {
                        pb.setProgressDrawable(res.getDrawable(R.drawable.yellowprogressbar));
                    }
                    if (progressStatus >= redLimit) {
                        pb.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar));
                    }
                }
            }
        }
        thread.start()
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