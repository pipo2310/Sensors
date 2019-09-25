package com.example.appsensores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ProgressBar
import android.os.Handler
import kotlin.concurrent.thread


class Semaforos : AppCompatActivity() {
    private var progressStatus = 50
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semaforos)

        // Get the widgets reference from XML layout
        var btn = findViewById<Button>(R.id.btn_agua)
        var tv = findViewById<TextView>(R.id.titulo)
        var pb = findViewById<ProgressBar>(R.id.bar_agua)

        btn.setOnClickListener {
            progressStatus = 0

            // Start the lengthy operation in a background thread
            val thread = Thread {
                while (progressStatus < 200) {
                    // Update the progress status
                    progressStatus += 5

                    // Try to sleep the thread for 20 milliseconds

                    try {
                        Thread.sleep(20)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    // Update the progress bar
                    handler.post {
                        pb.progress = progressStatus
                        // Show the progress on TextView
                        tv.text = progressStatus.toString() + ""
                        // If task execution completed
                        if (progressStatus == 200) {
                            // Set a message of completion
                            tv.text = "Operation completed."
                        }
                    }
                }
            }

            thread.start()
        }
    }
}