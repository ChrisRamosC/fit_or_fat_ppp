package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.io.Api1RegistroDeDatos
import com.gruponiche.fit_or_fat.io.response.VerificarCodigoResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Verificacion_codigo : AppCompatActivity() {
    // Declara los EditText
    private val api1RegistroDeDatos: Api1RegistroDeDatos by lazy {
        Api1RegistroDeDatos.create()
    }
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion_codigo)

        // Asigna cada EditText a una variable
        editText1 = findViewById(R.id.inputCode1)
        editText2 = findViewById(R.id.inputCode2)
        editText3 = findViewById(R.id.inputCode3)
        editText4 = findViewById(R.id.inputCode4)
        editText5 = findViewById(R.id.inputCode5)
        editText6 = findViewById(R.id.inputCode6)

        // Agrega un TextWatcher a cada EditText
        editText1.addTextChangedListener(CodeTextWatcher(editText2,editText1))
        editText2.addTextChangedListener(CodeTextWatcher(editText3,editText2))
        editText3.addTextChangedListener(CodeTextWatcher(editText4,editText3))
        editText4.addTextChangedListener(CodeTextWatcher(editText5,editText4))
        editText5.addTextChangedListener(CodeTextWatcher(editText6,editText5))
        editText6.addTextChangedListener(CodeTextWatcher(editText1,editText6))

        val btnValidarCodigoVerificacion=findViewById<Button>(R.id.btnValidarCodigoVerificacion)
                btnValidarCodigoVerificacion.setOnClickListener {
                    performVerificarCodigo()
                }
        val flechaAtrasRecuperarContrasena=findViewById<ImageView>(R.id.flechaAtrasRecuperarContrasena)
        flechaAtrasRecuperarContrasena.setOnClickListener {
            goToRecuperarContrasena()
        }
    }

    private fun goToRestablecerContrasena(codigo:String){
        val i= Intent(this, Restablecer_contrasena::class.java)
        i.putExtra("codigo", codigo)
        startActivity(i)
        finish()
    }

    private fun goToRecuperarContrasena(){
        val i= Intent(this, Recuperar_contrasena::class.java)
        startActivity(i)
        finish()
    }

    // TextWatcher para cambiar el foco a otro EditText cuando se ingresa un carácter
    inner class CodeTextWatcher(private val nextEditText: EditText, private val editText: EditText) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

            if (s?.isNotEmpty() == true) {

                nextEditText.requestFocus()
            }


        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private fun performVerificarCodigo(){



        // Asigna cada EditText a una variable
        editText1 = findViewById(R.id.inputCode1)
        editText2 = findViewById(R.id.inputCode2)
        editText3 = findViewById(R.id.inputCode3)
        editText4 = findViewById(R.id.inputCode4)
        editText5 = findViewById(R.id.inputCode5)
        editText6 = findViewById(R.id.inputCode6)


        if(  editText1.text.toString().isEmpty() || editText2.text.toString().isEmpty()  || editText3.text.toString().isEmpty()
            || editText4.text.toString().isEmpty() || editText5.text.toString().isEmpty() || editText6.text.toString().isEmpty()){
            Toast.makeText(applicationContext,"Ingrese el código completo", Toast.LENGTH_SHORT).show()
            return
        }

        val combinedText = editText1.text.toString() + editText2.text.toString() + editText3.text.toString() + editText4.text.toString()+ editText5.text.toString()+ editText6.text.toString()
        val call=api1RegistroDeDatos.postVerificarCodigo(combinedText)
        call.enqueue(object: Callback<VerificarCodigoResponse> {
            override fun onResponse(call: Call<VerificarCodigoResponse>, response: Response<VerificarCodigoResponse>) {

                if(response.isSuccessful){
                    val createResponse=response.body()
                    if(createResponse==null){
                        Toast.makeText(applicationContext,"Se produjo un error", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if(createResponse.estado==202){

                        goToRestablecerContrasena(combinedText)
                    }else{
                        Toast.makeText(applicationContext,createResponse.mensaje, Toast.LENGTH_SHORT).show()
                    }
                }else{
                    val gson = Gson()
                    val message: VerificarCodigoResponse? =
                        gson.fromJson(response.errorBody()!!.charStream(), VerificarCodigoResponse::class.java)
                    val mensaje = message?.mensaje ?: "Error desconocido"
                    Toast.makeText(applicationContext,mensaje, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<VerificarCodigoResponse>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}