package com.example.appsensores.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.appsensores.R
import com.example.appsensores.models.Cuenta
import com.example.appsensores.services.CuentasService
import com.example.appsensores.services.SensoresService
import com.example.appsensores.services.ServiceBuilder
import kotlinx.android.synthetic.main.another_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExpandableListAdapterSensores(var context: Context, var expandableListView : ExpandableListView, var header : MutableList<String>, var body : MutableList<MutableList<Long>>)  : BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_group,null)
        }
        val title = convertView?.findViewById<TextView>(R.id.tv_title)
        title?.text = getGroup(groupPosition)
        title?.setOnClickListener {
            if(expandableListView.isGroupExpanded(groupPosition))
                expandableListView.collapseGroup(groupPosition)
            else
                expandableListView.expandGroup(groupPosition)
            Toast.makeText(context, getGroup(groupPosition), Toast.LENGTH_SHORT).show()
        }
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Long {
        return body[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_child,null)
        }
        val title = convertView?.findViewById<TextView>(R.id.tv_title)
        if(childPosition == 0){
            title?.text = "Detalles"
            title?.setOnClickListener {
                var id = getChild(groupPosition,childPosition)
                var intent = Intent(context, DetallesSensor::class.java)
                intent.putExtra("idSensor", id)
                context.startActivity(intent)
            }
        }else if(childPosition == 1){
            title?.text = "Editar"
            title?.setOnClickListener {
                var id = getChild(groupPosition,childPosition)
                var intent = Intent(context, ModificarSensores::class.java)
                intent.putExtra("idSensor", id)
                context.startActivity(intent)
            }
        }else if(childPosition == 2){
            title?.text = "Eliminar"
            title?.setOnClickListener {
                var id = getChild(groupPosition,childPosition)
                accionDeEliminar(id)
            }
        }
        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }

    fun accionDeEliminar(id: Long) {

        val mDialog = LayoutInflater.from(context).inflate(R.layout.another_view, null);
        mDialog.deleteText.setText("¿Está seguro que desea eliminar la empresa ")

        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialog)
            .setTitle("Alerta")


        val mAlertDialog = mBuilder.show()

        mDialog.button_cancela.setOnClickListener{
            mAlertDialog.dismiss()
        }

        mDialog.button_elimina.setOnClickListener {

            val sensoresService: SensoresService = ServiceBuilder.buildService(
                SensoresService::class.java)

            val call: Call<Int> = sensoresService.borrarSensores(id)

            call.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful()){
                        Toast.makeText(context,
                            "Sensor eliminado exitosamente", Toast.LENGTH_SHORT).show()
                        var intent = Intent(context, VistaSensores::class.java)
                        context.startActivity(intent)
                    }else{
                        Toast.makeText(context,
                            "No se pudo eliminar", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Toast.makeText(context,
                        "No se pudo eliminar", Toast.LENGTH_SHORT).show()

                }
            })
            mAlertDialog.dismiss()
        }
    }

}