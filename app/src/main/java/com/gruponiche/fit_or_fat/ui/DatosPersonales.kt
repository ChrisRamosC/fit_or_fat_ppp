package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.R


class DatosPersonales :  AppCompatActivity()   {
    private var radioButtonText: CharSequence? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales)
        var editTextEdad=findViewById<EditText>(R.id.edadUsuario)
        var rdGroupGenero=findViewById<RadioGroup>(R.id.rdGenero)
        var flechaAtras=findViewById<ImageView>(R.id.flechaAtras)
        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        // Recuperar los datos guardados
        val idRadioButtonSeleccionado = sharedPreferences.getInt("id_radio_button_seleccionado", -1)
        val textoIngresado = sharedPreferences.getString("texto_ingresado", "")
        if (idRadioButtonSeleccionado != -1) {
            rdGroupGenero.check(idRadioButtonSeleccionado)
        }
        editTextEdad.setText(textoIngresado)

        flechaAtras.setOnClickListener{

            // Al menos un radio button ha sido seleccionado y el edit text no está vacío
            val radioButtonId: Int = rdGroupGenero.checkedRadioButtonId
            val radioButton: View = rdGroupGenero.findViewById(radioButtonId)
            val radioButton2: RadioButton = rdGroupGenero.findViewById(radioButtonId)
            radioButtonText = radioButton2.text.toString()
            val editor = sharedPreferences.edit()
            editor.putInt("id_radio_button_seleccionado", rdGroupGenero.checkedRadioButtonId)
            editor.putString("genero_seleccionado", radioButtonText as String)
            editor.putString("texto_ingresado", editTextEdad.text.toString())
            editor.apply()

            val intent: Intent = Intent(this, Terminos::class.java)
            startActivity(intent)
        }


        val botonSiguiente=findViewById<Button>(R.id.btnSiguiente)
        botonSiguiente.setOnClickListener{
            if (rdGroupGenero.checkedRadioButtonId != -1 && !editTextEdad.text.isEmpty()) {
                // Al menos un radio button ha sido seleccionado y el edit text no está vacío
                val radioButtonId: Int = rdGroupGenero.checkedRadioButtonId
                val radioButton: View = rdGroupGenero.findViewById(radioButtonId)
                val radioButton2: RadioButton = rdGroupGenero.findViewById(radioButtonId)
                radioButtonText = radioButton2.text.toString()
                val indice: Int = rdGroupGenero.indexOfChild(radioButton)
                if(indice==0){
                    // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences

                    val editor = sharedPreferences.edit()
                    editor.putInt("id_radio_button_seleccionado", rdGroupGenero.checkedRadioButtonId)
                    editor.putString("genero_seleccionado", radioButtonText as String)
                    editor.putString("texto_ingresado", editTextEdad.text.toString())
                    editor.apply()

                    val intent:Intent = Intent(this, ZonaObjetivoHombre::class.java)
                    startActivity(intent)

                }else{

                    // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences
                    val editor = sharedPreferences.edit()
                    editor.putInt("id_radio_button_seleccionado", rdGroupGenero.checkedRadioButtonId)
                    editor.putString("genero_seleccionado", radioButtonText as String)
                    editor.putString("texto_ingresado", editTextEdad.text.toString())
                    editor.apply()

                    val intent:Intent = Intent(this, ZonaObjetivoMujer::class.java)
                    startActivity(intent)
                }

            }else{
                Toast.makeText(applicationContext,"Ingrese todos sus datos",Toast.LENGTH_SHORT).show()
            }


        }
    }


}
