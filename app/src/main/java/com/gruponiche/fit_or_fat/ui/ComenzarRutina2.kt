package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.databinding.ActivityComenzarRutina2Binding
import com.gruponiche.fit_or_fat.io.response.EjercicioResponse
import java.io.Serializable

class ComenzarRutina2 : AppCompatActivity() {
    private lateinit var binding: ActivityComenzarRutina2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComenzarRutina2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEjercicioTerminado.setOnClickListener {
            val intent = Intent(this, ComenzarRutinaDescanso::class.java)
            startActivity(intent)
        }

        binding.flechaAtras.setOnClickListener {
            finish()
        }

        binding.btnSaltar.setOnClickListener {
            val intent = Intent(this, ComenzarRutinaDescanso::class.java)
            startActivity(intent)
        }

        val bundle = intent.extras
        val listaEjercicio = (bundle?.getSerializable("lista_ejercicios") as? List<EjercicioResponse>)!!
        val nombreRutina = bundle.getString("nombre_rutina")
        val cantidadEjercicios = listaEjercicio.size
        val actual = bundle.getInt("actual")
        if (actual < cantidadEjercicios) {
            if (nombreRutina != null) {
                iterarEjercicios(listaEjercicio, actual, nombreRutina)
            }
        }


    }

    private fun iterarEjercicios(lista: List<EjercicioResponse>, actual: Int, nombre: String) {
        Glide.with(binding.ivEjercicioRutina.context).load(lista[actual].gif)
            .into(binding.ivEjercicioRutina)
        binding.tvNombreEjercicio.text = lista[actual].nombre_ejercicio
        binding.tvRepeticiones.text = "x" + lista[actual].repeticiones.toString()
        binding.tvNombreRutina.text = nombre

        val intent = Intent(this, ComenzarRutinaDescanso::class.java)
        intent.putExtra("lista_ejercicios", lista as Serializable)
        intent.putExtra("nombre_rutina", nombre)
        intent.putExtra("actual", actual + 1)

        binding.btnEjercicioTerminado.setOnClickListener {
            startActivity(intent)
        }

        binding.btnSaltarEjercicio.setOnClickListener {
            startActivity(intent)
        }
    }
}