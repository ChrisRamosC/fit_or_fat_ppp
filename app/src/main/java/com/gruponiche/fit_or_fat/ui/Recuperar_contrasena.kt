package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.io.Api1RegistroDeDatos
import com.gruponiche.fit_or_fat.io.response.EnviarCorreoResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Recuperar_contrasena : AppCompatActivity() {
    private val api1RegistroDeDatos: Api1RegistroDeDatos by lazy {
        Api1RegistroDeDatos.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contrasena)
        val flechaAtrasIniciarSesion= findViewById<ImageView>(R.id.flechaAtrasIniciarSesion)
        flechaAtrasIniciarSesion.setOnClickListener {
            gotToPantallaInicio()
        }
        val btnAceptarRecuperarContrasena=findViewById<Button>(R.id.btnAceptarRecuperarContrasena)
        btnAceptarRecuperarContrasena.setOnClickListener {
            performEnviarCorreo()
        }
    }

    private fun gotToPantallaInicio(){
        val i= Intent(this, Iniciar_sesion::class.java)
        startActivity(i)
        finish()
    }

    private fun goToVerificacionCodigo(){
        val i= Intent(this, Verificacion_codigo::class.java)
        startActivity(i)
        finish()
    }

    private fun performEnviarCorreo(){


        val editCorreo=findViewById<EditText>(R.id.editTextCorreoRecuperarContrasena).text.toString()


        if( editCorreo.trim().isEmpty()){
            Toast.makeText(applicationContext,"Ingrese el correo", Toast.LENGTH_SHORT).show()
            return
        }


        val call=api1RegistroDeDatos.postEnviarCorreo(editCorreo)
        call.enqueue(object: Callback<EnviarCorreoResponse> {
            override fun onResponse(call: Call<EnviarCorreoResponse>, response: Response<EnviarCorreoResponse>) {

                if(response.isSuccessful){
                    val createResponse=response.body()
                    if(createResponse==null){
                        Toast.makeText(applicationContext,"Se produjo un error", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if(createResponse.estado==202){
                        goToVerificacionCodigo()
                    }else{
                        Toast.makeText(applicationContext,createResponse.mensaje, Toast.LENGTH_SHORT).show()
                    }
                }else{
                    val gson = Gson()
                    val message: EnviarCorreoResponse? =
                        gson.fromJson(response.errorBody()!!.charStream(), EnviarCorreoResponse::class.java)
                    val mensaje = message?.mensaje ?: "Error desconocido"
                    Toast.makeText(applicationContext,mensaje, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EnviarCorreoResponse>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}