package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.io.Api1RegistroDeDatos
import com.gruponiche.fit_or_fat.io.response.CreateResponse
import com.gruponiche.fit_or_fat.model.Usuario
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Crear_cuenta : AppCompatActivity() {
    private val api1RegistroDeDatos: Api1RegistroDeDatos by lazy {
        Api1RegistroDeDatos.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        val inciarSesion=findViewById<TextView>(R.id.textIniciarSesion)
        inciarSesion.setOnClickListener{
            gotToPantallaInicio()
        }
        val btnCrearCuenta=findViewById<Button>(R.id.btnCrearCuenta)
        btnCrearCuenta.setOnClickListener {
            performCrearCuenta()
        }
    }

    private fun performCrearCuenta(){

        val ediTextNombre=findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        val editTelefono=findViewById<EditText>(R.id.editTextPhone).text.toString()
        val editCorreo=findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        val editContrasena=findViewById<TextInputEditText>(R.id.crearEditPassoword).text.toString()
        val editConfirmarContrasena=findViewById<TextInputEditText>(R.id.crearEditPassowordConfirm).text.toString()

        if(ediTextNombre.trim().isEmpty() || editTelefono.trim().isEmpty() || editCorreo.trim().isEmpty() || editContrasena.trim().isEmpty() ||
            editConfirmarContrasena.trim().isEmpty()){
            Toast.makeText(applicationContext,"Ingrese todo sus datos", Toast.LENGTH_SHORT).show()
            return
        }
        if(editContrasena!=editConfirmarContrasena){
            Toast.makeText(applicationContext,"Su contraseña no coincide", Toast.LENGTH_SHORT).show()
            return
        }
        val usuario: Usuario = Usuario(ediTextNombre,editTelefono,editCorreo,editContrasena)
        val call=api1RegistroDeDatos.postCreateUsuario(usuario)
        call.enqueue(object: Callback<CreateResponse>{
            override fun onResponse(call: Call<CreateResponse>, response: Response<CreateResponse>) {

                if(response.isSuccessful){
                    val createResponse=response.body()
                    if(createResponse==null){
                        Toast.makeText(applicationContext,"Se produjo un error",Toast.LENGTH_SHORT).show()
                        return
                    }
                    if(createResponse.estado==201){
                        gotToPantallaInicio()
                    }else{
                        Toast.makeText(applicationContext,createResponse.mensaje,Toast.LENGTH_SHORT).show()
                    }
                }else{
                    val gson = Gson()
                    val message: CreateResponse =
                        gson.fromJson(response.errorBody()!!.charStream(), CreateResponse::class.java)
                    Toast.makeText(applicationContext,message.mensaje ,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CreateResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"Se produjo un error",Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun gotToPantallaInicio(){
        val i= Intent(this, Iniciar_sesion::class.java)
        startActivity(i)
        finish()
    }



}