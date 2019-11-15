package com.example.appsensores.activities.ui.dss

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.example.appsensores.R
import com.example.appsensores.models.Sensor
import com.example.appsensores.services.SensoresService
import com.example.appsensores.services.ServiceBuilder
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_agua.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AguaFragment : Fragment() {
    val tipo = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_agua, container, false)
        val textView: TextView = root.findViewById(R.id.texto)
        textView.text = "Hola Mundo"
        val linearL: LinearLayout = root.findViewById(R.id.linearlayout_agua)
        val sensoresService: SensoresService = ServiceBuilder.buildService(
            SensoresService::class.java)

        val call: Call<List<Sensor>> = sensoresService.getSensoresPorTipo(tipo)

        call.enqueue(object: Callback<List<Sensor>> {
            override fun onResponse(call: Call<List<Sensor>>, response: Response<List<Sensor>>) {
                if (!response.isSuccessful())
                {
                    // Mensaje de Error
                    return
                }
                val sensores = response.body()
                crearLista(sensores, linearL)

            }
            override fun onFailure(call: Call<List<Sensor>>, t:Throwable) {
                // Mostrar Error
                System.out.println("asads")
            }
        })
        return root
    }

    fun crearLista(lista: List<Sensor>, linearL:LinearLayout){
        for (sensor in lista){
            val linear = LayoutInflater.from(context).inflate(R.layout.elemento_sensor, null, false)
            val textView: TextView = linear.findViewById(R.id.nombreSensor)
            textView.text = sensor.nombre
            linearL.addView(linear)
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): AguaFragment {
            return AguaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
