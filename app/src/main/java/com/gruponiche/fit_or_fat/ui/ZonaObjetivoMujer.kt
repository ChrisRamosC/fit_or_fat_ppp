package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.gruponiche.fit_or_fat.R

class ZonaObjetivoMujer : AppCompatActivity() {
    private var radioButtonText: CharSequence? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zona_objetivo_mujer)

        var rdZonaMujer=findViewById<RadioGroup>(R.id.rdZonaMujer)
        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        // Recuperar los datos guardados
        val idRadioButtonSeleccionado = sharedPreferences.getInt("id_radio_button_zona_mujer", -1)

        if (idRadioButtonSeleccionado != -1) {
            rdZonaMujer.check(idRadioButtonSeleccionado)
        }
        val btnFlechaAtras=findViewById<ImageView>(R.id.flechaAtrasDatosPersonales)
        btnFlechaAtras.setOnClickListener {

            val radioButtonId: Int =rdZonaMujer.checkedRadioButtonId
            val radioButton: View = rdZonaMujer.findViewById(radioButtonId)
            val radioButton2: RadioButton = rdZonaMujer.findViewById(radioButtonId)
            radioButtonText = radioButton2.text.toString()
            // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences
            val editor = sharedPreferences.edit()
            editor.putInt("id_radio_button_zona_mujer", rdZonaMujer.checkedRadioButtonId)
            editor.putString("zona-seleccionado-mujer", radioButtonText as String)
            editor.apply()
            goToDatosPersonales()
        }

        val btnSiguienteMetricas=findViewById<Button>(R.id.btnSiguienteMetricasMujer)
        btnSiguienteMetricas.setOnClickListener {
            if (rdZonaMujer.checkedRadioButtonId != -1) {
                val radioButtonId: Int =rdZonaMujer.checkedRadioButtonId
                val radioButton: View = rdZonaMujer.findViewById(radioButtonId)
                val radioButton2: RadioButton = rdZonaMujer.findViewById(radioButtonId)
                radioButtonText = radioButton2.text.toString()
                // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences
                val editor = sharedPreferences.edit()
                editor.putInt("id_radio_button_zona_mujer", rdZonaMujer.checkedRadioButtonId)
                editor.putString("zona-seleccionado-mujer", radioButtonText as String)
                editor.apply()
                goToMetricas()

            }else{
                Toast.makeText(applicationContext,"Ingrese su zona objetivo", Toast.LENGTH_SHORT).show()
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