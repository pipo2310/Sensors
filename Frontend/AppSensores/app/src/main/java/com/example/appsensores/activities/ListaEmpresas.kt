package com.example.appsensores.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsensores.R
import com.example.appsensores.models.Cuenta
import com.example.appsensores.services.CuentasService
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaEmpresas : AppCompatActivity() {
    var listaEmpresas: List<Cuenta> = listOf(Cuenta())
    val tableLayout by lazy{ TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_empresas)
    }

    override fun onResume(){
        super.onResume()
        recuperarCuentas()
    }

    fun recuperarCuentas(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/cuentas/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cuentasService: CuentasService = retrofit.create(CuentasService::class.java)

        val call: Call<List<Cuenta>> = cuentasService.getCuentas()

        call.enqueue(object: Callback<List<Cuenta>> {
            override fun onResponse(call:Call<List<Cuenta>>, response: Response<List<Cuenta>>) {
                if (!response.isSuccessful())
                {
                    return
                }
                listaEmpresas= response.body()
                crearTabla()
            }
            override fun onFailure(call:Call<List<Cuenta>>, t:Throwable) {
                // Mostrar Error
            }
        })

    }

    fun crearTabla(){
        var tabla: TableLayout = findViewById<TableLayout>(R.id.tabla1)
        val layoutManager = FlexboxLayoutManager(this)
        var encabezados = TableRow(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        var recyclerView : RecyclerView = RecyclerView(this)
        recyclerView.layoutManager = layoutManager
        val tv = TextView(this)
        tv.text = "Nombre"
        recyclerView.addView(tv)
        val tv2 = TextView(this)
        tv2.text = "Editar"
        recyclerView.addView(tv2)
        encabezados.addView(recyclerView)
        val divLikeContent = ArrayList<String>()
        divLikeContent.add("Route")
        divLikeContent.add("Safety")
        divLikeContent.add("Smell")
        val adapter = DivLikeAdapter(divLikeContent)
        recyclerView.adapter = adapter
        tabla.addView(encabezados)

        for (cuenta in listaEmpresas){
            val row = TableRow(this)
            row.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            var nombreCuenta = TextView(this)
            nombreCuenta.text = cuenta.nombre
            row.addView(nombreCuenta)
            tabla.addView(row)
        }
    }
}
