package com.example.appsensores.activities.ui.dss


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView

import com.example.appsensores.R

class GasFragment : Fragment() {
    protected lateinit var lv: ListView
    protected lateinit var searchView: SearchView
    protected lateinit var adapter: ArrayAdapter<String>
    var data = arrayOf("Sensor1", "Sensor2", "Sensor3", "Sensor4")
    var data2 = arrayOf(ItemSensor("Sensor2", "5%"), ItemSensor("Sensor2", "5%"), ItemSensor("Sensor2", "5%"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_gas, container, false)
        lv = view.findViewById(R.id.idListView)
        adapter = ArrayAdapter<String>(this.context!!, android.R.layout.simple_list_item_2, data)
        lv.adapter = adapter
        return view
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
        fun newInstance(sectionNumber: Int): GasFragment {
            return GasFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
