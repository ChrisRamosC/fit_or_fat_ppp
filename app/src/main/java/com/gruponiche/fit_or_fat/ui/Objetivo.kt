package com.gruponiche.fit_or_fat.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.io.Api1RegistroDeDatos
import com.gruponiche.fit_or_fat.io.response.RegistrarDatosResponse
import com.gruponiche.fit_or_fat.model.DatosUsuario
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Objetivo : AppCompatActivity() {
    private val api1RegistroDeDatos: Api1RegistroDeDatos by lazy {
        Api1RegistroDeDatos.create()
    }
    private var radioButtonText: CharSequence? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objetivo)
        var rdGroupObjetivo=findViewById<RadioGroup>(R.id.radioGroupObjetivo)

        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        // Recuperar los datos guardados
        val idRadioButtonSeleccionado = sharedPreferences.getInt("id_radio_button_objetivo", -1)
        if (idRadioButtonSeleccionado != -1) {
            rdGroupObjetivo.check(idRadioButtonSeleccionado)
        }

        val btnFlechaNivel=findViewById<ImageView>(R.id.flechaAtrasNivel)
        btnFlechaNivel.setOnClickListener {
            // Al menos un radio button ha sido seleccionado y el edit text no está vacío

            val radioButtonId: Int =  rdGroupObjetivo.checkedRadioButtonId
            val radioButton2: RadioButton =  rdGroupObjetivo.findViewById(radioButtonId)
            radioButtonText = radioButton2.text.toString()


            // Guardar el radio button seleccionado y el texto ingresado en Shared Preferences

            val editor = sharedPreferences.edit()
            editor.putInt("id_radio_button_objetivo",  rdGroupObjetivo.checkedRadioButtonId)
            editor.putString("objetivo_seleccionado", radioButtonText as String)
            editor.apply()
            goToNivel()
        }

        val btnGuardar=findViewById<Button>(R.id.GuardarDatosUsuario)
        btnGuardar.setOnClickListener {
            if ( rdGroupObjetivo.checkedRadioButtonId != -1 ) {
                val dialogBinding = layoutInflater.inflate(R.layout.background_layout,null)
                val myDialog= Dialog(this)
                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val aceptar = dialogBinding.findViewById<ImageView>(R.id.aceptarGuardar)
                val cancelar = dialogBinding.findViewById<ImageView>(R.id.rechazarGuardar)

                aceptar.setOnClickListener {
                    performIngresarDatos()
                }


                cancelar.setOnClickListener {
                    myDialog.dismiss()
                }


                myDialog.show()


            }else{
                Toast.makeText(applicationContext,"Ingrese todos sus datos", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun goToNivel(){
        val intent: Intent = Intent(this, Nivel::class.java)
        startActivity(intent)
    }

    private fun goToPantallaPrincipal(){
        val intent: Intent = Intent(this, MenuPrincipal::class.java)
        startActivity(intent)
    }

    private fun performIngresarDatos(){

        // Obtener una instancia de Shared Preferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
        var rdGroupObjetivo=findViewById<RadioGroup>(R.id.radioGroupObjetivo)

        // Al menos un radio button ha sido seleccionado y el edit text no está vacío
        val radioButtonId: Int = rdGroupObjetivo.checkedRadioButtonId
        val radioButton: View = rdGroupObjetivo.findViewById(radioButtonId)
        val radioButton2: RadioButton = rdGroupObjetivo.findViewById(radioButtonId)
        var objetivo= radioButton2.text.toString().lowercase(Locale.getDefault()).replace("\n", " ")
//              Recuperar un valor String almacenado
        var genero= sharedPreferences.getString("genero_seleccionado", "")
        var edad= sharedPreferences.getString("texto_ingresado","")
        var zona=""
        if(genero=="Hombre"){
            genero="M"
             zona= sharedPreferences.getString("zona-seleccionado-hombre","").toString()
                 .lowercase(Locale.getDefault())
                 .replace("\n", " ")
        }else{
            genero="F"
             zona= sharedPreferences.getString("zona-seleccionado-mujer","").toString()
                 .lowercase(Locale.getDefault())
                 .replace("\n", " ")
        }
        var talla= sharedPreferences.getString("texto_ingresado-talla", "")
        var peso=  sharedPreferences.getString("texto_ingresado-peso", "")
        var nivel= sharedPreferences.getString("nivel_seleccionado", "").toString()
            .lowercase(Locale.getDefault())
            .replace("\n", " ")

        // Obtener una instancia de Shared Preferences
        val sharedPreferences2 = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        // Recuperar un valor entero almacenado
        val id = sharedPreferences2.getInt("id_usuario", 0)

        val datosUsuario: DatosUsuario = DatosUsuario( edad.toString(),genero,peso.toString(),talla.toString(), nivel,zona,objetivo)
        val call=api1RegistroDeDatos.postRegistrarDatos(id,datosUsuario)
        call.enqueue(object: Callback<RegistrarDatosResponse> {
            override fun onResponse(call: Call<RegistrarDatosResponse>, response: Response<RegistrarDatosResponse>) {

                if(response.isSuccessful){
                    val createResponse=response.body()
                    if(createResponse==null){
                        Toast.makeText(applicationContext,"Se produjo un error",Toast.LENGTH_SHORT).show()
                        return
                    }
                    if(createResponse.estado==200){
                        goToPantallaPrincipal()

                    }else{
                        Toast.makeText(applicationContext,createResponse.mensaje,Toast.LENGTH_SHORT).show()
                    }
                }else{
                    val gson = Gson()
                    val message: RegistrarDatosResponse? =
                        gson.fromJson(response.errorBody()!!.charStream(), RegistrarDatosResponse::class.java)
                    val mensaje = message?.mensaje ?: "Error desconocido"
                    Toast.makeText(applicationContext,mensaje, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegistrarDatosResponse>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }

        })

    }



}