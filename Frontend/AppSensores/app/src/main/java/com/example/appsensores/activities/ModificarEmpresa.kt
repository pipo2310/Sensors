package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appsensores.R
import com.example.appsensores.models.Cuenta
import com.example.appsensores.services.CuentasService
import com.example.appsensores.services.ServiceBuilder
import com.example.appsensores.utilities.Validacion
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lista_de_empresas.toolbar
import kotlinx.android.synthetic.main.activity_modificar_empresa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModificarEmpresa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_empresa)
        toolbar.title = "Modificar Cuenta"
        setSupportActionBar(toolbar)

        val invalido = -1.toLong()
        var idEmpresa = invalido
        idEmpresa = intent.getLongExtra("idEmpresa", idEmpresa)
        if(idEmpresa == invalido) {
            intent = Intent(this, ListaDeEmpresas::class.java)
            startActivity(intent)
        }else {
            val cuentasService: CuentasService = ServiceBuilder.buildService(
                CuentasService::class.java)

            val call: Call<Cuenta> = cuentasService.getCuenta(idEmpresa)

            call.enqueue(object: Callback<Cuenta> {
                override fun onResponse(call: Call<Cuenta>, response: Response<Cuenta>) {
                    if (!response.isSuccessful())
                    {
                        Snackbar.make(modificar_empresa_layout, "No se pudo actualizar el usuario, intente de nuevo o mas tarde", Snackbar.LENGTH_SHORT).show()
                        return
                    }

                    val cuenta = response.body()
                    llenarCampos(cuenta)
                }
                override fun onFailure(call: Call<Cuenta>, t:Throwable) {
                    Snackbar.make(modificar_empresa_layout, "No se pudo actualizar el usuario, intente de nuevo o mas tarde", Snackbar.LENGTH_SHORT).show()
                    startActivity(intent)
                }
            })
        }
    }

    fun llenarCampos(cuenta :Cuenta){
        val nombreEmpresa = findViewById<EditText>(R.id.nombreEmpresa)
        nombreEmpresa.setText(cuenta.nombre.toString())
        val telefono = findViewById<EditText>(R.id.telefono)
        telefono.setText(cuenta.telefono)
        val email = findViewById<EditText>(R.id.email)
        email.setText(cuenta.email)
        val clave = findViewById<EditText>(R.id.clave)
        val codigo = findViewById<EditText>(R.id.codigo)
        codigo.setText(cuenta.codigo)
        val seccion = findViewById<EditText>(R.id.seccionEmpresa)
        seccion.setText(cuenta.seccion)

        val btnActualizar = findViewById<Button>(R.id.actualizarE)
        btnActualizar.setOnClickListener {
            btnActualizar.isClickable = false
            var correcto = true
            var validacion: Validacion = Validacion()
            var cuenta = Cuenta()
            cuenta.nombre = nombreEmpresa.text.toString()
            if(!validacion.validacionAlfaNumerica(cuenta.nombre)){
                Toast.makeText(this, "Nombre Incorrecto, debe ser alfanumerico", Toast.LENGTH_LONG).show()
                correcto = false
            }
            cuenta.telefono = telefono.text.toString()
            if(!validacion.validacionNumerica(cuenta.telefono)){
                Toast.makeText(this, "Numero de Telefono Incorrecto, debe ser numerico, 8 digitos", Toast.LENGTH_LONG).show()
                correcto = false
            }
            cuenta.email = email.text.toString() // ID
            if(!validacion.validacionEmail(cuenta.email)){
                Toast.makeText(this, "Email Incorrecto, debe ser un email valido", Toast.LENGTH_LONG).show()
                correcto = false
            }
            cuenta.codigo = codigo.text.toString()
            if(!validacion.validacionCodigo(cuenta.codigo)){
                Toast.makeText(this, "Codigo Incorrecto, debe ser alfanumerica y letras mayusculas", Toast.LENGTH_LONG).show()
                correcto = false
            }
            cuenta.seccion = seccion.text.toString()
            if(!validacion.validacionAlfaNumerica(cuenta.seccion)){
                Toast.makeText(this, "Seccion Incorrecta, debe ser alfanumerica", Toast.LENGTH_LONG).show()
                correcto = false
            }
            val clave = clave.text.toString()
            if(correcto) {

                val cuentasService: CuentasService = ServiceBuilder.buildService(
                    CuentasService::class.java
                )

                val call: Call<Cuenta> = cuentasService.updateCuenta(cuenta)

                intent = Intent(this, ListaDeEmpresas::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

                call.enqueue(object : Callback<Cuenta> {
                    override fun onResponse(call: Call<Cuenta>, response: Response<Cuenta>) {
                        if (!response.isSuccessful()) {
                            // Mensaje de Error
                            btnActualizar.isClickable = true
                            return
                        }
                        val cuenta = response.body()
                        // Falta actualizar en FireBase
                        // Preguntar si la clave es vacia no cambiarla
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<Cuenta>, t: Throwable) {
                        // Mensaje de Error
                        btnActualizar.isClickable = true

                    }
                })
            }else{
                btnActualizar.isClickable = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item.itemId == R.id.sensores){
            intent = Intent(this, VistaSensores::class.java)
            startActivity(intent)
        }else if (item.itemId == R.id.semaforos)
        {
            intent = Intent(this, Semaforos::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
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
