package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.gruponiche.fit_or_fat.R

class Metricas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metricas)
        var editTextTalla=findViewById<EditText>(R.id.talla)
        var editTextPeso=findViewById<EditText>(R.id.peso)
        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        // Recuperar los datos guardados

        val textoIngresadoTalla = sharedPreferences.getString("texto_ingresado-talla","" )
        val textoIngresadoPeso = sharedPreferences.getString("texto_ingresado-peso", "")
        editTextTalla.setText(textoIngresadoTalla.toString())
        editTextPeso.setText(textoIngresadoPeso.toString())



        val btnFlechaAtras=findViewById<ImageView>(R.id.flechaAtrasZonaObjetivo)
        btnFlechaAtras.setOnClickListener {
            // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences
            val editor = sharedPreferences.edit()
            editor.putString("texto_ingresado-talla",editTextTalla.text.toString() )
            editor.putString("texto_ingresado-peso", editTextPeso.text.toString())
            editor.apply()
            goToNivel()
            goToZonaObjetivo()
        }

        val btnSiguienteNivel=findViewById<Button>(R.id.btnSiguienteNivel)
        btnSiguienteNivel.setOnClickListener {
            if (!editTextTalla.text.isEmpty() && !editTextPeso.text.isEmpty()) {
                // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences
                val editor = sharedPreferences.edit()
                editor.putString("texto_ingresado-talla",editTextTalla.text.toString() )
                editor.putString("texto_ingresado-peso", editTextPeso.text.toString())
                editor.apply()
                goToNivel()
            }else{
                Toast.makeText(applicationContext,"Ingrese todos sus datos",Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun goToNivel(){
        val intent: Intent = Intent(this, Nivel::class.java)
        startActivity(intent)
    }
    private fun goToZonaObjetivo(){
        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

// Recuperar el índice del radio button seleccionado
        val generoSeleccionado = sharedPreferences.getString("genero_seleccionado", "")

// Crear un intent para la actividad correspondiente
        val intent: Intent
        if (generoSeleccionado == "Hombre") {
            intent = Intent(this, ZonaObjetivoHombre::class.java)
            // Iniciar la actividad
            startActivity(intent)
        } else  {
            intent = Intent(this, ZonaObjetivoMujer::class.java)
            // Iniciar la actividad
            startActivity(intent)
        }


    }
}