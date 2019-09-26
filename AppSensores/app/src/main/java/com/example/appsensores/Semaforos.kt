package com.example.appsensores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.os.Handler
import android.widget.*
import androidx.core.graphics.toColor
import kotlin.concurrent.thread
import androidx.constraintlayout.solver.widgets.WidgetContainer.getBounds




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
        intent = Intent(this, ModificarLimites::class.java)

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
        yellowLimit1 = intent.getIntExtra("yellowlimA", yellowLimit1)
        var text1 = findViewById<TextView>(R.id.titulo)
        text1.text = yellowLimit1.toString()
        redLimit1 = intent.getIntExtra("redlimA", redLimit1)
        yellowLimit2 = intent.getIntExtra("yellowlimG", yellowLimit1)
        redLimit2 = intent.getIntExtra("redlimG", redLimit2)
        yellowLimit3 = intent.getIntExtra("yellowlimE", yellowLimit3)
        redLimit3 = intent.getIntExtra("redlimE", redLimit3)
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


    fun aumentarBarra(pb: ProgressBar?, yellowLimitParam: Int, redLimitParam: Int, totalLimit: Int, idSem: Int) {
        var yellowLimit = yellowLimitParam
        var redLimit = redLimitParam
        //var tv = findViewById<TextView>(R.id.titulo)
        val res = resources
        val thread = Thread {
            pb!!.max = totalLimit
            while (progressStatus < totalLimit) {

                if (idSem == 0){
                    yellowLimit = yellowLimit1
                    redLimit = redLimit1
                }else if (idSem == 1){
                    yellowLimit = yellowLimit2
                    redLimit = redLimit2
                }else{
                    yellowLimit = yellowLimit3
                    redLimit = redLimit3
                }
                // Update the progress status
                progressStatus += 1

                // Try to sleep the thread for 20 milliseconds
                //Escuchando si cambian los valores limites

                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                // Update the progress bar
                handler.post {
                    pb.progress = progressStatus
                    // Show the progress on TextView
                    if (progressStatus >= yellowLimit) {
                        pb.setProgressDrawable(res.getDrawable(R.drawable.yellowprogressbar));
                    }
                    // If task execution completed
                    if (progressStatus >= redLimit) {
                        pb.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar));
                    }
                }
            }
        }
        thread.start()
    }
}