package com.example.appsensores.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.appsensores.R
import com.example.appsensores.activities.lista.CrearEmpresa
import com.example.appsensores.activities.lista.CustomAdapter
import com.example.appsensores.models.Cuenta
import com.example.appsensores.services.CuentasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.appsensores.activities.lista.DataItem


class ListaEmpresas : AppCompatActivity() {
    var listaEmpresas: List<Cuenta> = listOf(Cuenta())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_empresas)
    }

    override fun onResume(){
        super.onResume()
        recuperarCuentas()
        var crear = findViewById<Button>(R.id.crearEmpresa)
        crear.setOnClickListener {
            intent = Intent(this, CrearEmpresa::class.java)
            startActivity(intent)
        }
    }

    fun recuperarCuentas(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-3-86-67-165.compute-1.amazonaws.com/sensores-backend/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cuentasService: CuentasService = retrofit.create(CuentasService::class.java)

        val call: Call<List<Cuenta>> = cuentasService.getCuentas()

        call.enqueue(object: Callback<List<Cuenta>> {
            override fun onResponse(call:Call<List<Cuenta>>, response: Response<List<Cuenta>>) {
                if (!response.isSuccessful())
                {
                    mostrarMensaje("*Error al contactar con la base de datos*")
                    return
                }
                listaEmpresas = response.body()
                // Se verifica si hay empresas en la BD
                if(listaEmpresas.isNotEmpty()){
                    // Si hay empresas mostramos la lista de ellas
                    crearTabla()
                }else{
                    // Si no hay empresas mostramos un mensaje indicandolo
                    mostrarMensaje("No hay empresas que mostrar")
                }

            }
            override fun onFailure(call:Call<List<Cuenta>>, t:Throwable) {
                // Mostrar Error
                mostrarMensaje("Error al contactar con la base de datos")
            }
        })

    }

    fun crearTabla(){
        // Se obtiene la tabla
        var tabla = findViewById<TableLayout>(R.id.tabla1)
        // Se crea la fila
        var fila = TableRow(this)

        // Se crea el textView para el encabezado de nombre de la empresa
        var nombreE = TextView(this)
        nombreE.text = "Nombre Empresa"
        nombreE.gravity = 1
        nombreE.setTextSize(24.0f)
        nombreE.setTypeface(null, Typeface.BOLD)
        // Se agrega el textView a la fila
        fila.addView(nombreE, 550, 80)

        // Se crea el textView para el encabezado de Editar
        val editarTV = TextView(this)
        editarTV.text = "Editar"
        editarTV.gravity = 1
        editarTV.setTextSize(24.0f)
        editarTV.setTypeface(null, Typeface.BOLD)
        // Se agrega el textView a la fila
        fila.addView(editarTV, 250, 80)

        // Se crea el textView para el encabezado de Eliminar
        val borrarTV = TextView(this)
        borrarTV.text = "Eliminar"
        borrarTV.gravity = 1
        borrarTV.setTextSize(24.0f)
        borrarTV.setTypeface(null, Typeface.BOLD)
        // Se agrega el textView a la fila
        fila.addView(borrarTV, 250, 80)

        // Se agrega la fila a la tabla
        tabla.addView(fila)

        for (cuenta in listaEmpresas) {
            // Se crea la fila
            fila = TableRow(this)

            // Se crea el textView para el encabezado de Editar
            nombreE = TextView(this)
            nombreE.text = cuenta.nombre
            nombreE.setTextSize(24.0f)
            nombreE.setPadding(50, 0, 0, 0)
            // Se agrega el textView a la fila
            fila.addView(nombreE, 200, 100)

            // Se crea el boton de editar
            val editarBtn = ImageButton(this)
            editarBtn.setBackgroundColor(Color.parseColor("#00ff0000"))
            editarBtn.setOnClickListener {
                //accionDeEditar(cuenta.usuario)
            }
            editarBtn.setImageResource(R.drawable.edit32)
            // Se agrega el boton de editar a la fila
            fila.addView(editarBtn, 100, 100)

            // Se crea el boton de eliminar
            val borrarBtn = ImageButton(this)
            borrarBtn.setBackgroundColor(Color.parseColor("#00ff0000"))
            borrarBtn.setOnClickListener {
                //accionDeEliminar(cuenta.usuario)
            }
            borrarBtn.setImageResource(R.drawable.delete32)
            // Se agrega el boton de eliminar a la fila
            fila.addView(borrarBtn, 100, 100)

            // Se agrega la fila a la tabla
            tabla.addView(fila)
        }
    }

    fun mostrarMensaje(msj: String){
        // Se obtiene la tabla
        var tabla = findViewById<TableLayout>(R.id.tabla1)

        // Se crea la fila
        var fila = TableRow(this)

        // Se crea el textView para el mensaje
        var mensaje = TextView(this)
        mensaje.text = msj
        mensaje.gravity = 1
        mensaje.textSize = 24.0f
        mensaje.setTypeface(null, Typeface.BOLD)

        // Se agrega el textView a la fila
        fila.addView(mensaje, 800, 200)

        // Se muestra mensaje en la tabla
        tabla.addView(fila)
    }
}
