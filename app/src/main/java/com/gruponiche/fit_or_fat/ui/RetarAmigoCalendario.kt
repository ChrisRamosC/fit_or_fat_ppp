package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.databinding.ActivityRetarAmigoCalendarioBinding
import com.gruponiche.fit_or_fat.io.Api3RetarAmigoRutinas
import com.gruponiche.fit_or_fat.io.response.CompetenciaResponse
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import com.gruponiche.fit_or_fat.model.Amigo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RetarAmigoCalendario : AppCompatActivity() {
    private lateinit var binding: ActivityRetarAmigoCalendarioBinding
    private lateinit var amigo: Amigo
    private lateinit var rutina: RutinaResponse
    private lateinit var horaReto: String
    private lateinit var fechaReto: String
//    val sharedPreferencesUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE)
//    val id = sharedPreferencesUsuario.getInt("id_usuario", 0)

    private val api3RetarAmigoRutinas: Api3RetarAmigoRutinas by lazy {
        Api3RetarAmigoRutinas.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetarAmigoCalendarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        amigo = (bundle?.getSerializable("datos_amigo") as? Amigo)!!
        rutina = (bundle.getSerializable("datos_rutina") as? RutinaResponse)!!


        var calendar = Calendar.getInstance()
        val minDate = calendar.timeInMillis
        binding.calendarView.minDate = minDate

        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var month = calendar.get(Calendar.MONTH) + 1
        var year = calendar.get(Calendar.YEAR)

        fechaReto = "$day/$month/$year"

        binding.calendarView.setOnDateChangeListener { view, i, i2, i3 ->

            day = i3
            month = i2 + 1
            year = i

            fechaReto = "$day/$month/$year"
        }


        binding.btnEnviarSolicitud.setOnClickListener {

            if (it.isEnabled) {

                val call = api3RetarAmigoRutinas.postRegistrarCompetencia(/*amigo.id*/1,/*rutina.id*/1, 2)
                call.enqueue(object: Callback<CompetenciaResponse> {
                    override fun onResponse(call: Call<CompetenciaResponse>, response: Response<CompetenciaResponse>) {
                        startRetarAmigoSolicitud()
                    }

                    override fun onFailure(call: Call<CompetenciaResponse>, t: Throwable) {
                    }


                })

            } else {
                val toast = Toast.makeText(this, "Seleccione una hora", Toast.LENGTH_SHORT)
                toast.show()
            }


        }

        binding.etTime.setOnClickListener {
            showTimePickerDialog()
        }

        binding.flechaAtras4.setOnClickListener {
            finish()
        }


    }
    fun startRetarAmigoSolicitud() {
        val intent = Intent(this, RetarAmigoSolicitud::class.java)
        intent.putExtra("datos_amigo", amigo)
        intent.putExtra("datos_rutina", rutina )
        intent.putExtra("fecha", fechaReto)
        intent.putExtra("hora", horaReto)
        startActivity(intent)
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment {onTimeSelected(it)}
        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelected(time: String){
        binding.btnEnviarSolicitud.isEnabled = true
        binding.etTime.hint = time
        horaReto = time
    }
}