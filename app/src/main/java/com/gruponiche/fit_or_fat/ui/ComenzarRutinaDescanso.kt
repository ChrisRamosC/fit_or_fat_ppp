package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.RutinaFinalizada
import com.gruponiche.fit_or_fat.databinding.ActivityComenzarRutinaDescansoBinding
import com.gruponiche.fit_or_fat.io.response.EjercicioResponse
import com.google.android.gms.ads.AdRequest
import java.io.Serializable

class ComenzarRutinaDescanso : AppCompatActivity() {
    private lateinit var binding: ActivityComenzarRutinaDescansoBinding
    private lateinit var timer: CountDownTimer
    private var segundosActuales: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComenzarRutinaDescansoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadAds()

        binding.flechaAtras.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        val listaEjercicio = (bundle?.getSerializable("lista_ejercicios") as? List<EjercicioResponse>)!!
        val nombreRutina = bundle.getString("nombre_rutina")
        val cantidadEjercicios = listaEjercicio.size
        val actual = bundle.getInt("actual")
        if (actual < cantidadEjercicios) {
            if (nombreRutina != null) {
                iterarEjercicios(cantidadEjercicios, listaEjercicio, actual, nombreRutina)
            }
        } else {
            val intent = Intent(this, RutinaFinalizada::class.java)
            intent.putExtra("nombre_rutina", nombreRutina)
            intent.putExtra("cantidad", cantidadEjercicios)
            startActivity(intent)
        }


    }

    private fun iterarEjercicios(
        cantidad: Int,
        lista: List<EjercicioResponse>,
        actual: Int,
        nombre: String
    ) {

        binding.tvEjercicioNombreCantidad.text =
            lista[actual].nombre_ejercicio + " x" + lista[actual].repeticiones

        Glide.with(binding.ivImagenEjercicio.context).load(lista[actual].gif)
            .into(binding.ivImagenEjercicio)
        binding.tvNombreRutina4.text = nombre

        var pausado: Boolean = false

        countDown(lista[actual - 1].descanso_seg.toLong() * 1000, lista, actual,nombre)



        binding.btnSaltarDescanso.setOnClickListener {
            nextEjercicio(lista, actual, nombre)
            timer.cancel()
        }

        binding.btnPausar.setOnClickListener {
            if (!pausado) {
                binding.btnPausar.text = "Despausar"
                pausado = true
                timer.cancel()
            } else {
                binding.btnPausar.text = "Pausar"
                pausado = false
                countDown(segundosActuales, lista, actual, nombre)
            }
        }
    }

    private fun countDown(
        segundos: Long,
        lista: List<EjercicioResponse>,
        actual: Int,
        nombre: String
    ) {
        timer = object : CountDownTimer(segundos, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTiempo.text = "" + millisUntilFinished / 1000
                segundosActuales = millisUntilFinished
            }

            override fun onFinish() {
                nextEjercicio(lista, actual, nombre)
            }
        }.start()
    }

    private fun nextEjercicio(lista: List<EjercicioResponse>, actual: Int, nombre: String) {

        val intent = Intent(this, ComenzarRutina2::class.java)
        intent.putExtra("lista_ejercicios", lista as Serializable)
        intent.putExtra("nombre_rutina", nombre)
        intent.putExtra("actual", actual)
        startActivity(intent)

    }

    private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
    }
}