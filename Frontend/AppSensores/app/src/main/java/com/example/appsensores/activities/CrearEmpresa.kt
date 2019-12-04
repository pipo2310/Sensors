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
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_crear_empresa.*
import kotlinx.android.synthetic.main.activity_crear_sensores.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrearEmpresa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_empresa)
        toolbar.title = "Crear Empresa"
        setSupportActionBar(toolbar)

        val nombreEmpresa = findViewById<EditText>(R.id.nombreEmpresa)
        val telefono = findViewById<EditText>(R.id.telefono)
        val email = findViewById<EditText>(R.id.email)
        val clave = findViewById<EditText>(R.id.clave)
        val codigo = findViewById<EditText>(R.id.codigo)
        val seccion = findViewById<EditText>(R.id.seccionEmpresa)

        val btnCrear = findViewById<Button>(R.id.crearE)
        btnCrear.setOnClickListener {
            btnCrear.isClickable = false
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
            val clave = clave.text.toString()
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
            if(correcto) {
                val cuentasService: CuentasService = ServiceBuilder.buildService(
                    CuentasService::class.java)

                val call: Call<Cuenta> = cuentasService.insertCuenta(cuenta)

                intent = Intent(this, ListaDeEmpresas::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

                call.enqueue(object: Callback<Cuenta> {
                    override fun onResponse(call: Call<Cuenta>, response: Response<Cuenta>) {
                        if (!response.isSuccessful())
                        {
                            Snackbar.make(crear_empresa_layout, "No se pudo crear el usuario, intente de nuevo o mas tarde", Snackbar.LENGTH_SHORT).show()
                            btnCrear.isClickable = true
                            return
                        }
                        val cuenta = response.body()
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(cuenta.email, clave).addOnCompleteListener { task: Task<AuthResult> ->
                            if (task.isSuccessful) {
                                val firebaseUser = FirebaseAuth.getInstance().currentUser!!
                            } else {
                                //Registration error
                                Snackbar.make(crear_empresa_layout, "No se pudo crear el usuario", Snackbar.LENGTH_SHORT).show()
                            }
                        }
                        startActivity(intent)
                    }
                    override fun onFailure(call: Call<Cuenta>, t:Throwable) {
                        Snackbar.make(crear_empresa_layout, "No se pudo crear el usuario, intente de nuevo o mas tarde", Snackbar.LENGTH_SHORT).show()
                        btnCrear.isClickable = true

                    }
                })
            }else{
                btnCrear.isClickable = true
            }
        }
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
