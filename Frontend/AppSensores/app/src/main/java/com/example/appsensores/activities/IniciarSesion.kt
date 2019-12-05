package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.appsensores.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*


class IniciarSesion : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    private var emailAuth: Boolean = true

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        iniciar_sesion_button.setOnClickListener(this)
        iniciar_sesion_google_button.setOnClickListener(this)
        olvido_contrasena.setOnClickListener(this)
        iniciar_sesion_codigo_button.setOnClickListener(this)
        iniciar_sesion_correo_button.setOnClickListener(this)
        contrasena_editText.setOnClickListener(this)
        codigo_editText.setOnClickListener(this)
        enviar_correo_button.setOnClickListener(this)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        signOut()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("GoogleActivity", "Google sign in failed", e)
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }
        }else{
            signOut()
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        auth.fetchSignInMethodsForEmail(acct.email.toString())
            .addOnCompleteListener(this) { task ->
                val isNewUser = task.result?.signInMethods?.isEmpty()
                if (isNewUser!!) {
                    signOut()
                    Toast.makeText(this,"El correo ${acct.email} no se encuentra registrado en el sistema.",Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("GoogleActivity", "firebaseAuthWithGoogle:" + acct.id!!)
                    val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener(this){
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("GoogleActivity", "signInWithCredential:success")
                                Toast.makeText(this,"Autenticación Exitosa: Usuario de Google ${acct.email}",Toast.LENGTH_SHORT).show()
                                updateUI(auth.currentUser)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("GoogleActivity", "signInWithCredential:failure", task.exception)
                                Toast.makeText(this, "Autenticación Fallida: error de red o del servidor por favor compruebe su señal de internet", Toast.LENGTH_LONG).show()
                                updateUI(null)
                            }
                        }
                }
            }

    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signInWithEmail() {
        val email = findViewById<EditText>(R.id.correo_electronico_editText).text.toString()
        val password = findViewById<EditText>(R.id.contrasena_editText).text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            this.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        updateUI(auth.currentUser)
                        Toast.makeText(this, "Autenticación Exitosa: Usuario $email", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Autenticación Fallida: credenciales inválidos", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        } else if (email.isEmpty()){
            Toast.makeText(this, "El campo correo no puede estar vacío", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "El campo contraseña no puede estar vacío", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInWithCode() {
        val business = findViewById<EditText>(R.id.empresa_editText).text.toString()
        val code = findViewById<EditText>(R.id.codigo_editText).text.toString()
        if (business.isNotEmpty() && code.isNotEmpty()) {
            //consulta = bla
//            if(business == consulta[x] && code == consulta[y]) {
                signInAnonymously(business)
//                adminEmail = consulta[z]
//            }else{
//                Toast.makeText(this, "Autenticación Fallida: credenciales inválidos", Toast.LENGTH_LONG).show()
//            }
        } else if(business.isEmpty()){
            Toast.makeText(this, "El campo empresa no puede estar vacío", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "El campo código no puede estar vacío", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInAnonymously(business: String){
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    updateUI(auth.currentUser)
                    Toast.makeText(this, "Usuario Anónimo de $business", Toast.LENGTH_SHORT).show()
                } else {
                    updateUI(null)
                    Toast.makeText(this, "Autenticación Fallida: error de red o del servidor por favor compruebe su señal de internet", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun signIn() {
        if(emailAuth) {
            signInWithEmail()
        }else{
            signInWithCode()
        }
    }

    private fun signOut() {
        val user = auth.currentUser
        if(user != null) {
            if (user.isAnonymous) {
                user.delete().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("GoogleActivity", "User account deleted.")
                    }
                }
                adminEmail = ""
            }
        }
        // Firebase sign out
        auth.signOut()
        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            updateUI(null)
        }
    }

    private fun forgotPassword(){
        println("password send to email")
        val email = findViewById<EditText>(R.id.correo_electronico_editText).text.toString()
         auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Correo electrónico enviado a $email", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "Error: no se logró enviar el correo a $email", Toast.LENGTH_LONG).show()
                }
            }
        showSignInEmail()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
            finish()
        }else{
            showSignInEmail()
        }
    }

    /**
     * Cambia como se muestra la pantalla de inicio, a un inicio con el códgio de la empresa
     */
    private fun showForgotPassword() {
        enviar_correo_button.visibility = View.VISIBLE
        iniciar_sesion_correo_button.visibility = View.VISIBLE

        olvido_contrasena_grid.visibility = View.GONE
        contrasena_editText.visibility = View.GONE
        olvido_contrasena.visibility = View.GONE
        iniciar_sesion_codigo_button.visibility = View.GONE
        iniciar_sesion_google_button.visibility = View.GONE
        iniciar_sesion_button.visibility = View.GONE

    }


    /**
     * Cambia como se muestra la pantalla de inicio, a un inicio con el códgio de la empresa
     */
    private fun showSignInCode() {
        emailAuth = false
        empresa_editText.visibility = View.VISIBLE
        codigo_editText.visibility = View.VISIBLE
        iniciar_sesion_correo_button.visibility = View.VISIBLE

        correo_electronico_editText.visibility = View.GONE
        contrasena_editText.visibility = View.GONE
        olvido_contrasena.visibility = View.GONE
        iniciar_sesion_codigo_button.visibility = View.GONE
        iniciar_sesion_google_button.visibility = View.GONE
    }

    /**
     * Cambia como se muestra la pantalla de inicio, a un inicio con correo electrónico.
     */
    private fun showSignInEmail() {
        emailAuth = true
        empresa_editText.visibility = View.GONE
        codigo_editText.visibility = View.GONE
        iniciar_sesion_correo_button.visibility = View.GONE
        enviar_correo_button.visibility = View.GONE

        correo_electronico_editText.visibility = View.VISIBLE
        contrasena_editText.visibility = View.VISIBLE
        olvido_contrasena_grid.visibility = View.VISIBLE
        olvido_contrasena.visibility = View.VISIBLE
        iniciar_sesion_button.visibility = View.VISIBLE
        iniciar_sesion_codigo_button.visibility = View.VISIBLE
        iniciar_sesion_google_button.visibility = View.VISIBLE
    }

    private fun togglePassword(passwordEditText: EditText) {
        if (passwordEditText.transformationMethod.javaClass.simpleName == "PasswordTransformationMethod") {
            passwordEditText.transformationMethod = SingleLineTransformationMethod()
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, R.drawable.ic_visibility, 0)
        }
        else {
            passwordEditText.transformationMethod = PasswordTransformationMethod()
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, R.drawable.ic_visibility_off, 0)
        }
        passwordEditText.setSelection(passwordEditText.text.length)
    }

    /**
     * Lista de acciones al hacer clic en cada botón
     */
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.contrasena_editText -> togglePassword(contrasena_editText)
            R.id.codigo_editText -> togglePassword(codigo_editText)
            R.id.iniciar_sesion_google_button -> signInWithGoogle()
            R.id.iniciar_sesion_button -> signIn()
            R.id.olvido_contrasena -> showForgotPassword()
            R.id.iniciar_sesion_codigo_button -> showSignInCode()
            R.id.iniciar_sesion_correo_button -> showSignInEmail()
            R.id.enviar_correo_button -> forgotPassword()
        }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
//        import com.example.appsensores.activities.IniciarSesion.Companion.adminEmail // para saber a que empresa pertenece el usuario anónimo

        var adminEmail: String = ""
    }
}