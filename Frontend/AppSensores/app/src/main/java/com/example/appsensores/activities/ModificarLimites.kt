package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.appsensores.R
import com.example.appsensores.models.Cuenta
import com.example.appsensores.services.CuentasService
import com.example.appsensores.services.ServiceBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_modificar_empresa.*
import kotlinx.android.synthetic.main.activity_modificar_limites.*
import kotlinx.android.synthetic.main.activity_semaforos.*
import kotlinx.android.synthetic.main.activity_vista_sensores.*
import kotlinx.android.synthetic.main.activity_vista_sensores.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModificarLimites : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_limites)
        toolbar.title = "Modificar Limites"
        setSupportActionBar(toolbar)

        var user = FirebaseAuth.getInstance().currentUser
        var empresaId = "wal@gmail.com"
        if(user != null){
            //empresaId = user.email.orEmpty()
        }else{
            //empresaId = "a" // codigoEmpresa //
        }

        val cuentasService: CuentasService = ServiceBuilder.buildService(
            CuentasService::class.java)

        val call: Call<Cuenta> = cuentasService.getCuenta2(empresaId)

        call.enqueue(object: Callback<Cuenta> {
            override fun onResponse(call: Call<Cuenta>, response: Response<Cuenta>) {
                if (!response.isSuccessful())
                {
                    Snackbar.make(modificar_limites_layout, "Ocurrio un error, intente luego", Snackbar.LENGTH_SHORT).show()
                    return
                }

                val cuenta = response.body()
                llenarCampos(cuenta)
            }
            override fun onFailure(call: Call<Cuenta>, t:Throwable) {
                Snackbar.make(modificar_limites_layout, "Ocurrio un error, intente luego", Snackbar.LENGTH_SHORT).show()
                startActivity(intent)
            }
        })
    }

    fun llenarCampos(cuenta: Cuenta){
        val minA = findViewById<EditText>(R.id.min_agua)
        minA.setText(cuenta.limiteAguaMedio.toString())
        val maxA = findViewById<EditText>(R.id.max_agua)
        maxA.setText(cuenta.limiteAguaAlto.toString())
        val minG = findViewById<EditText>(R.id.min_gas)
        minG.setText(cuenta.limiteGasMedio.toString())
        val maxG = findViewById<EditText>(R.id.max_gas)
        maxG.setText(cuenta.limiteGasAlto.toString())
        val minE = findViewById<EditText>(R.id.min_elect)
        minE.setText(cuenta.limiteElectMedio.toString())
        val maxE = findViewById<EditText>(R.id.max_elect)
        maxE.setText(cuenta.limiteElectAlto.toString())

        val btnAceptar = findViewById<Button>(R.id.btn_aceptar)
        btnAceptar.setOnClickListener{
            cuenta.limiteAguaMedio = minA.text.toString().toLong()
            cuenta.limiteAguaAlto = maxA.text.toString().toLong()
            cuenta.limiteGasMedio = minG.text.toString().toLong()
            cuenta.limiteGasAlto = maxG.text.toString().toLong()
            cuenta.limiteElectMedio = minE.text.toString().toLong()
            cuenta.limiteElectAlto = maxE.text.toString().toLong()

            val cuentasService: CuentasService = ServiceBuilder.buildService(
                CuentasService::class.java
            )

            val call: Call<Cuenta> = cuentasService.updateCuenta(cuenta)

            intent = Intent(this, Semaforos::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

            call.enqueue(object : Callback<Cuenta> {
                override fun onResponse(call: Call<Cuenta>, response: Response<Cuenta>) {
                    if (!response.isSuccessful()) {
                        Snackbar.make(modificar_limites_layout, "Ocurrio un error, intente otra vez", Snackbar.LENGTH_SHORT).show()
                        btn_modi.isClickable = true
                        return
                    }
                    val cuenta = response.body()
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent)
                    finish()
                }

                override fun onFailure(call: Call<Cuenta>, t: Throwable) {
                    Snackbar.make(modificar_limites_layout, "Ocurrio un error, intente otra vez", Snackbar.LENGTH_SHORT).show()
                    btn_modi.isClickable = true

                }
            })
            /*
            intent = Intent(this, Semaforos::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)*/
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
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
