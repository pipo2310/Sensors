package com.example.appsensores.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.appsensores.R
import com.example.appsensores.models.Cuenta
import com.example.appsensores.services.CuentasService
import com.example.appsensores.services.ServiceBuilder

import kotlinx.android.synthetic.main.activity_lista_de_empresas.*
import kotlinx.android.synthetic.main.activity_lista_de_empresas.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaDeEmpresas : AppCompatActivity() {
    var listaEmpresas: List<Cuenta> = listOf(Cuenta())
    val header: MutableList<String> = ArrayList()
    val body: MutableList<MutableList<Long>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_empresas)
        setSupportActionBar(toolbar)
    }

    override fun onResume(){
        super.onResume()
        recuperarCuentas()
        crearEmp.setOnClickListener { view ->
            intent = Intent(this, CrearEmpresa::class.java)
            startActivity(intent)
        }
    }

    fun recuperarCuentas(){
        /*
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/cuentas/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/

        val cuentasService: CuentasService =
            ServiceBuilder.buildService(
                CuentasService::class.java
            )

        val call: Call<List<Cuenta>> = cuentasService.getCuentas()

        call.enqueue(object: Callback<List<Cuenta>> {
            override fun onResponse(call: Call<List<Cuenta>>, response: Response<List<Cuenta>>) {
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
            override fun onFailure(call: Call<List<Cuenta>>, t:Throwable) {
                // Mostrar Error
                mostrarMensaje("Error al contactar con la base de datos")
            }
        })

    }

    fun crearTabla() {
        header.clear()
        body.clear()
        for (cuenta in listaEmpresas) {
            val atributos: MutableList<Long> = ArrayList()
            header.add(cuenta.nombre)
            atributos.add(cuenta.cuentasPk)
            atributos.add(cuenta.cuentasPk)
            atributos.add(cuenta.cuentasPk)
            body.add(atributos)
        }
        expandableListView.setAdapter(ExpandableListAdapterEmpresas(this, expandableListView, header, body))
    }

    fun mostrarMensaje(msj: String){
        /*
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
        */
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.itemId == R.id.sensores){
            intent = Intent(this, VistaSensores::class.java)
            startActivity(intent)
        }else if (item.itemId == R.id.semaforos)
        {
            intent = Intent(this, Semaforos::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }else if (item.itemId == R.id.costos)
        {
            intent = Intent(this, Costos::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }else if(item.itemId == R.id.cerrar_sesion){
            finishAffinity()
            setResult(R.id.cerrar_sesion)
            startActivity(Intent(this, IniciarSesion::class.java))
        }else if(item.itemId == R.id.historicos){
            intent = Intent(this, Historicos::class.java)
            startActivity(intent)
        }else if (item.itemId == R.id.empresas)
        {
            intent = Intent(this, ListaDeEmpresas::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }else{
            super.onOptionsItemSelected(item)
        }
        return true
    }
}
