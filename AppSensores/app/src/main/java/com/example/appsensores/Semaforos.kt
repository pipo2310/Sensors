package com.example.appsensores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ProgressBar
import android.os.Handler
import androidx.core.graphics.toColor
import kotlin.concurrent.thread
import androidx.constraintlayout.solver.widgets.WidgetContainer.getBounds




class Semaforos : AppCompatActivity() {
    private var progressStatus = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semaforos)

        for(i in 0 until 3){
            if(i==0){
                //tv.text = "soy 0"
                val pb = findViewById<ProgressBar>(R.id.bar_agua)
                val yellowLimit1 = 45
                val redLimit1 = 75
                val totalLimit1 = 100
                aumentarBarra(pb, yellowLimit1, redLimit1, totalLimit1)
            }
            else if(i==1){
                //tv.text = "soy 1"
                val pb = findViewById<ProgressBar>(R.id.bar_gas)
                val yellowLimit2 = 50
                val redLimit2 = 130
                val totalLimit2 = 200
                aumentarBarra(pb, yellowLimit2, redLimit2, totalLimit2)
            }
            else{
                val pb = findViewById<ProgressBar>(R.id.bar_elect)
                val yellowLimit3 = 125
                val redLimit3 = 275
                val totalLimit3 = 300
                aumentarBarra(pb, yellowLimit3, redLimit3, totalLimit3)
            }
            // Start the lengthy operation in a background thread

        }
    }

    fun aumentarBarra(pb: ProgressBar?, yellowLimit: Int, redLimit: Int, totalLimit: Int) {
        var tv = findViewById<TextView>(R.id.titulo)
        val res = resources
        val thread = Thread {
            pb!!.max = totalLimit
            while (progressStatus < totalLimit) {
                // Update the progress status
                progressStatus += 1

                // Try to sleep the thread for 20 milliseconds

                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                // Update the progress bar
                handler.post {
                    pb.progress = progressStatus
                    // Show the progress on TextView
                    tv.text = progressStatus.toString() + ""
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