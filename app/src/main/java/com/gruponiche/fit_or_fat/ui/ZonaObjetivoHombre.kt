package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.R

class ZonaObjetivoHombre : AppCompatActivity() {
    private var radioButtonText: CharSequence? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zona_objetivo_hombre)
        var rdZonaHombre=findViewById<RadioGroup>(R.id.rdZonaHombre)
        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        // Recuperar los datos guardados
        val idRadioButtonSeleccionado = sharedPreferences.getInt("id_radio_button_zona", -1)

        if (idRadioButtonSeleccionado != -1) {
            rdZonaHombre.check(idRadioButtonSeleccionado)
        }


        val btnFlechaAtras=findViewById<ImageView>(R.id.flechaAtrasDatosPersonales)
        btnFlechaAtras.setOnClickListener {

            val radioButtonId: Int =rdZonaHombre.checkedRadioButtonId
            val radioButton: View = rdZonaHombre.findViewById(radioButtonId)
            val radioButton2: RadioButton = rdZonaHombre.findViewById(radioButtonId)
            radioButtonText = radioButton2.text.toString()

            // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences
            val editor = sharedPreferences.edit()
            editor.putInt("id_radio_button_zona", rdZonaHombre.checkedRadioButtonId)
            editor.putString("zona-seleccionado-hombre", radioButtonText as String)
            editor.apply()
            goToDatosPersonales()
        }

        val btnSiguienteMetricas=findViewById<Button>(R.id.btnSiguienteMetricasHombre)
        btnSiguienteMetricas.setOnClickListener {
            if (rdZonaHombre.checkedRadioButtonId != -1) {
                val radioButtonId: Int =rdZonaHombre.checkedRadioButtonId
                val radioButton: View = rdZonaHombre.findViewById(radioButtonId)
                val radioButton2: RadioButton = rdZonaHombre.findViewById(radioButtonId)
                radioButtonText = radioButton2.text.toString()

                // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences
                val editor = sharedPreferences.edit()
                editor.putInt("id_radio_button_zona", rdZonaHombre.checkedRadioButtonId)
                editor.putString("zona-seleccionado-hombre", radioButtonText as String)
                editor.apply()
                goToMetricas()

            }else{
                Toast.makeText(applicationContext,"Ingrese su zona objetivo",Toast.LENGTH_SHORT).show()
            }

        }





    }

    private fun goToDatosPersonales(){
        val intent: Intent = Intent(this, DatosPersonales::class.java)
        startActivity(intent)
    }

    private fun goToMetricas(){
        val intent: Intent = Intent(this, Metricas::class.java)
        startActivity(intent)
    }
}