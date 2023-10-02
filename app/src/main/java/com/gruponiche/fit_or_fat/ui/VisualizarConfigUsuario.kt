package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.databinding.ActivityVisualizarConfigUsuarioBinding
import com.gruponiche.fit_or_fat.util.PreferenceHelper

class VisualizarConfigUsuario:AppCompatActivity() {

    private lateinit var binding: ActivityVisualizarConfigUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizarConfigUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.flechaAtras.setOnClickListener {
            finish()
        }

        // Obtener una instancia de Shared Preferences
        val sharedPreferences3 = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        // Recuperar un valor entero almacenado
        val nombre = sharedPreferences3.getString("nombre_usuario", "")
        Log.i("nombre",nombre.toString())

        binding.tvNombreCompleto1.text = nombre
        /*
        binding.txtCerrarSesion.setOnClickListener(){
            clearSessionPreference()
            goToLogin()
        }
        */
        binding.conviertetePremium.setOnClickListener {
            goToConviertetePremium()
        }

        binding.txtCerrarSesion.setOnClickListener {
            clearSessionPreference()
            goToLogin()
        }

    }

    private fun goToLogin(){
        val i= Intent(this, Iniciar_sesion::class.java)
        startActivity(i)
        finish()

    }

    private fun clearSessionPreference(){
        val preferences= PreferenceHelper.defaultPrefs(this)
//        preferences["token"]=""
    }

    private fun goToConviertetePremium() {
        val intent = Intent (this, ConviertetePremium::class.java)
        startActivity(intent)
    }

    /*
    private fun goToLogin() {
        val i = Intent(this, Iniciar_sesion::class.java)
        startActivity(i)
        finish()
    }

    private fun clearSessionPreference() {
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["token"] = ""
    }
    */

}


