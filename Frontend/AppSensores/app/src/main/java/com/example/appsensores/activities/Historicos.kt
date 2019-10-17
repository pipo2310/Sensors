package com.example.appsensores.activities

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


class Historicos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historicos)
        val points = arrayOf(
            Point(1.0, 178.0),
            Point(2.0, 179.0),
            Point(3.0, 179.0),
            Point(4.0, 181.0),
            Point(5.0, 180.0),
            Point(6.0, 182.0),
            Point(7.0, 182.0),
            Point(8.0, 184.0),
            Point(9.0, 183.0),
            Point(10.0, 185.0),
            Point(11.0, 185.0),
            Point(12.0, 186.0)
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
        setTitle("Empty Graph")
        val textView = findViewById<TextView>(R.id.graph_view_label)
        textView.setText("Graph of Axes")
    }
}
