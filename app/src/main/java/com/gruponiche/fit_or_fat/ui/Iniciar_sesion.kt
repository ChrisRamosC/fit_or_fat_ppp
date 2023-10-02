package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.io.Api1RegistroDeDatos
import com.gruponiche.fit_or_fat.io.response.LoginResponse
import com.gruponiche.fit_or_fat.util.PreferenceHelper
import com.gruponiche.fit_or_fat.util.PreferenceHelper.set
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Iniciar_sesion : AppCompatActivity() {
    private val api1RegistroDeDatos: Api1RegistroDeDatos by lazy {
        Api1RegistroDeDatos.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inciar_sesion)
        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
        val editorDatosUsuario = sharedPreferences.edit()
        editorDatosUsuario.clear()
        editorDatosUsuario.apply()
        val editor = getSharedPreferences("token", MODE_PRIVATE).edit()
        editor.clear().apply()
        //val preferences = PreferenceHelper.defaultPrefs(this)
        //if(preferences["token", ""].contains(".")){
        //    goToPantallaPrincipal()
        //}

        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        btnIniciarSesion.setOnClickListener {
            performLogin()
        }

        val crearCuenta = findViewById<TextView>(R.id.textCrearCuenta)
        crearCuenta.setOnClickListener {
            goToCrearCuenta()
        }

        val recuperarContrasena = findViewById<TextView>(R.id.olvideContrasena)
        recuperarContrasena.setOnClickListener {
            goToRecuperarContrasena()
        }
    }

    private fun goToPantallaPrincipal() {
        val i = Intent(this, MenuPrincipal::class.java)
        startActivity(i)
        finish()
    }

    private fun createSessionPreference(token: String) {
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["token"] = token
    }

    private fun goToCrearCuenta() {
        val i = Intent(this, Crear_cuenta::class.java)
        startActivity(i)
        finish()
    }

    private fun goToRecuperarContrasena() {
        val i = Intent(this, Recuperar_contrasena::class.java)
        startActivity(i)
        finish()
    }

    private fun goToPatanllaTerminos() {
        val i = Intent(this, Terminos::class.java)
        startActivity(i)
        finish()
    }

    private fun goToComenzarRutina() {
        val i = Intent(this, ComenzarRutina::class.java)
        startActivity(i)
        finish()
    }

    private fun performLogin() {

        val editEmail = findViewById<EditText>(R.id.editEmail).text.toString()
        val editContrasena = findViewById<TextInputEditText>(R.id.textInputPassword).text.toString()
        if (editEmail.trim().isEmpty() || editContrasena.trim().isEmpty()) {
            Toast.makeText(applicationContext, "Ingrese su correo y contraseña", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val call = api1RegistroDeDatos.postLogin(editEmail, editContrasena)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("prueba", response.body().toString())
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse == null) {
                        Toast.makeText(
                            applicationContext,
                            "Se produjo un error",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                    if (loginResponse.estado == 200) {
                        createSessionPreference(loginResponse.token)
                        if (loginResponse.usuario.es_autenticado == false) {
                            // Obtener una instancia de SharedPreferences.Editor para modificar los datos
                            val sharedPreferencesUsuario =
                                getSharedPreferences("usuario", Context.MODE_PRIVATE)
                            // Obtener una instancia de SharedPreferences.Editor para modificar los datos
                            val editor = sharedPreferencesUsuario.edit()
                            // Guardar el usuario y el ID en los Shared Preferences
                            editor.putInt("id_usuario", loginResponse.usuario.codigo)
                            Log.d("iduser",loginResponse.usuario.codigo.toString())
                            editor.putString("nombre_usuario", loginResponse.usuario.nombre)
                            // Aplicar los cambios
                            editor.apply()
                            goToPatanllaTerminos()
                        } else {
                            // Obtener una instancia de SharedPreferences.Editor para modificar los datos
                            val sharedPreferencesUsuario =
                                getSharedPreferences("usuario", Context.MODE_PRIVATE)
                            // Obtener una instancia de SharedPreferences.Editor para modificar los datos
                            val editor = sharedPreferencesUsuario.edit()
                            // Guardar el usuario y el ID en los Shared Preferences
                            editor.putInt("id_usuario", loginResponse.usuario.codigo)
                            Log.d("iduser",loginResponse.usuario.codigo.toString())
                            editor.putString("nombre_usuario", loginResponse.usuario.nombre)
                            Log.i("nombre1", loginResponse.usuario.nombre)
                            editor.apply()
                            goToPantallaPrincipal()
                        }

                    } else {
                        Log.d("prueba", editEmail)
                        Log.d("prueba", editContrasena)
                        goToPantallaPrincipal()
                        Toast.makeText(
                            applicationContext,
                            "Las creedenciales son incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Log.d("prueba", editEmail)
                    Log.d("prueba", editContrasena)
                    goToPantallaPrincipal()
                    Toast.makeText(
                        applicationContext,
                        "Las creedenciales son incorrectas",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Se produjo un error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}

