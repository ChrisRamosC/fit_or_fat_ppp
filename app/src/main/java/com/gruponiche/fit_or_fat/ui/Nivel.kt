package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.gruponiche.fit_or_fat.R

class Nivel : AppCompatActivity() {
    private var radioButtonText: CharSequence? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel)
        var rdGroupActividad=findViewById<RadioGroup>(R.id.rdActividad)
        val btnFlechaMetricas=findViewById<ImageView>(R.id.flechaAtrasMetricas)


        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        // Recuperar los datos guardados
        val idRadioButtonSeleccionado = sharedPreferences.getInt("id_radio_button_nivel", -1)
        if (idRadioButtonSeleccionado != -1) {
            rdGroupActividad.check(idRadioButtonSeleccionado)
        }

        btnFlechaMetricas.setOnClickListener {

            // Al menos un radio button ha sido seleccionado y el edit text no está vacío
            val radioButtonId: Int = rdGroupActividad.checkedRadioButtonId
            val radioButton2: RadioButton = rdGroupActividad.findViewById(radioButtonId)
            radioButtonText = radioButton2.text.toString()


            // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences

            val editor = sharedPreferences.edit()
            editor.putInt("id_radio_button_nivel", rdGroupActividad.checkedRadioButtonId)
            editor.putString("nivel_seleccionado", radioButtonText as String)
            editor.apply()
            goToMetricas()
        }

        val btnSiguienteObjetivo=findViewById<Button>(R.id.btnSiguienteObjetivo)
        btnSiguienteObjetivo.setOnClickListener {
            if (rdGroupActividad.checkedRadioButtonId != -1 ) {
                // Al menos un radio button ha sido seleccionado y el edit text no está vacío
                val radioButtonId: Int = rdGroupActividad.checkedRadioButtonId
                val radioButton: View = rdGroupActividad.findViewById(radioButtonId)
                val radioButton2: RadioButton = rdGroupActividad.findViewById(radioButtonId)
                radioButtonText = radioButton2.text.toString()


                    // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences

                    val editor = sharedPreferences.edit()
                    editor.putInt("id_radio_button_nivel", rdGroupActividad.checkedRadioButtonId)
                    editor.putString("nivel_seleccionado", radioButtonText as String)
                    editor.apply()
                    goToZonaObjetivo()



            }else{
                Toast.makeText(applicationContext,"Ingrese todos sus datos", Toast.LENGTH_SHORT).show()
            }


        }



    }

    private fun goToMetricas(){
        val intent: Intent = Intent(this, Metricas::class.java)
        startActivity(intent)
    }

    private fun goToZonaObjetivo(){
        val intent: Intent = Intent(this, Objetivo::class.java)
        startActivity(intent)
    }
}