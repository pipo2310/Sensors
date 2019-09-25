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


class Semaforos : AppCompatActivity() {
    private var progressStatus = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semaforos)

        // Get the widgets reference from XML layout
        val btn = findViewById(R.id.btn) as Button
        val tv = findViewById(R.id.titulo) as TextView
        val pb = findViewById(R.id.bar_agua) as ProgressBar

        btn.setOnClickListener {
            // Set the progress status zero on each button click
            progressStatus = 0

            // Start the lengthy operation in a background thread
            Thread(Runnable {
                while (progressStatus < 100) {
                    // Update the progress status
                    progressStatus += 1

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
                        if (progressStatus == 100) {
                            // Set a message of completion
                            tv.text = "Operation completed."
                        }
                    }
                }
            }).start() // Start the operation
        }
    }
}