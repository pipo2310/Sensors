package com.example.appsensores.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*


class IniciarSesion : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        iniciar_sesion_button.setOnClickListener(this)
        iniciar_sesion_google_button.setOnClickListener(this)
        olvido_contrasena.setOnClickListener(this)

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

    //TODO: Poner esto en la creación del usuario
//    this.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
//        if (task.isSuccessful) {
//            //Registration OK
//            val firebaseUser = this.firebaseAuth.currentUser!!
//        } else {
//            //Registration error
//        }
//    }

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
                Log.w(TAG, "Google sign in failed", e)
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }
        }else{
            signOut()
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Snackbar.make(iniciar_sesion_layout, "Authentication Fallida.", Snackbar.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signIn() {

        var user = findViewById<EditText>(R.id.nombre_usuario_editText).text.toString()
        //TODO:Obtener email de la base dependiendo de la entrada del usuario
        var email = user
        var password = findViewById<EditText>(R.id.contrasena_editText).text.toString()
        // TODO: esto no se puede utilizar debido a error con las dependencias y duplicados de clases generados, buscar solución
        //        println(BCryptPasswordEncoder().encode(password))

        if(!email.isEmpty() && !password.isEmpty()){
            this.auth.signInWithEmailAndPassword(email,password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    updateUI(auth.currentUser)
                    Toast.makeText(this, "Authentication Exitosa", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Authentication Fallida", Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            Toast.makeText(this, "Por favor llene los campos", Toast.LENGTH_SHORT).show()
        }

        auth.currentUser?.sendEmailVerification()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                    }
                }
    }

    private fun forgotPassword(){
        // TODO: utilizar firebase para ello
        //TODO: enviar un correo electrónico al usuario que lo requiera, para cambiar la contraseña, hacer tambíen vista para cambiar la contraseña al recivir un código
        println("password send to email")
        var user = findViewById<EditText>(R.id.nombre_usuario_editText).text.toString()
        //TODO:Obtener email de la base dependiendo de la entrada del usuario
        var email = user
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                    }
                }
    }

    private fun signOut() {
        // Firebase sign out
        auth.signOut()
        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            updateUI(null)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
            finish()
        }
    }

    override fun onClick(v: View?) {
        val i = v?.id
        when(i){
            R.id.iniciar_sesion_google_button -> signInGoogle()
            R.id.iniciar_sesion_button -> signIn()
            R.id.olvido_contrasena -> forgotPassword()
        }
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
        private const val RC_SIGN_OUT = 9002
    }
}