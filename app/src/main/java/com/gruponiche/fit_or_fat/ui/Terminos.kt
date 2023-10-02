package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.util.PreferenceHelper
import com.gruponiche.fit_or_fat.util.PreferenceHelper.set

class Terminos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminos)
        val botonAceptarTerminos=findViewById<Button>(R.id.buttonAceptarTerminos)
        botonAceptarTerminos.setOnClickListener{
            val intent: Intent = Intent(this, DatosPersonales ::class.java)
            startActivity(intent)
        }

        val btnRechazarTerminos=findViewById<Button>(R.id.btnRechazarTerminos)
        btnRechazarTerminos.setOnClickListener {
            clearSessionPreference()
            val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            goToLogin()
        }
    }

    private fun clearSessionPreference(){
        val preferences= PreferenceHelper.defaultPrefs(this)
        preferences["token"]=""
    }

    private fun goToLogin(){
        val i= Intent(this, Iniciar_sesion::class.java)
        startActivity(i)
        finish()

    }
}