package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.io.Api1RegistroDeDatos
import com.gruponiche.fit_or_fat.io.response.RestablecerContrasenaResponse
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Restablecer_contrasena : AppCompatActivity() {
    private val api1RegistroDeDatos: Api1RegistroDeDatos by lazy {
        Api1RegistroDeDatos.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restablecer_contrasena)
        val flechaAtrasVerificacion=findViewById<ImageView>(R.id.flechaAtrasVerificacion)
        flechaAtrasVerificacion.setOnClickListener {
            goToVerificacion_codigo()
        }

        val btnAceptarCambiarContrasena=findViewById<Button>(R.id.btnAceptarCambiarContrasena)
        btnAceptarCambiarContrasena.setOnClickListener {
            performRestablecerCotnrasena()
        }
    }

    private fun goToVerificacion_codigo(){
        val i= Intent(this, Verificacion_codigo::class.java)
        startActivity(i)
        finish()
    }

    private fun gotToPantallaInicio(){
        val i= Intent(this, Iniciar_sesion::class.java)
        startActivity(i)
        finish()
    }

    private fun performRestablecerCotnrasena(){

        val editContrasena=findViewById<TextInputEditText>(R.id.recuperarEditPassoword).text.toString()
        val editContrasenaConfirmar=findViewById<TextInputEditText>(R.id.recupearEditPassowordConfirm).text.toString()
        if(editContrasena != editContrasenaConfirmar){
            Toast.makeText(applicationContext,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }
        if( editContrasena.trim().isEmpty() || editContrasenaConfirmar.trim().isEmpty() ){
            Toast.makeText(applicationContext,"Ingrese todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = intent
        val value = intent.getStringExtra("codigo").toString()

        val call=api1RegistroDeDatos.postRestablecerContrasena(editContrasena,value)
        call.enqueue(object: Callback<RestablecerContrasenaResponse> {
            override fun onResponse(call: Call<RestablecerContrasenaResponse>, response: Response<RestablecerContrasenaResponse>) {

                if(response.isSuccessful){
                    val createResponse=response.body()
                    if(createResponse==null){
                        Toast.makeText(applicationContext,"Se produjo un error", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if(createResponse.estado==202){
                        gotToPantallaInicio()
                    }else{
                        Toast.makeText(applicationContext,createResponse.mensaje, Toast.LENGTH_SHORT).show()
                    }
                }else{
                    val gson = Gson()
                    val message: RestablecerContrasenaResponse? =
                        gson.fromJson(response.errorBody()!!.charStream(), RestablecerContrasenaResponse::class.java)
                    val mensaje = message?.mensaje ?: "Error desconocido"
                    Toast.makeText(applicationContext,mensaje, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RestablecerContrasenaResponse>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }


}