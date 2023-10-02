package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.databinding.ActivityRetarAmigoSeleccionBinding
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import com.gruponiche.fit_or_fat.model.Amigo

class RetarAmigoSeleccion : AppCompatActivity() {
    private lateinit var binding: ActivityRetarAmigoSeleccionBinding
    private lateinit var amigo: Amigo
    private lateinit var rutina: RutinaResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetarAmigoSeleccionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        amigo = (bundle?.getSerializable("datos_amigo") as? Amigo)!!
        rutina = (bundle.getSerializable("datos_rutina") as? RutinaResponse)!!

        binding.tvNombreAmigo.text = amigo.nombre
        Glide.with(binding.ivFotoAmigo.context).load(amigo.imgAmigo)
            .into(binding.ivFotoAmigo)

        Glide.with(binding.ivRutina.context).load(rutina.linkImagen)
            .into(binding.ivRutina)

        binding.tvRutinaNombre.text = rutina.nombreRutina
        binding.tvRutinaInfo.text = "| " + rutina.nivelRutina + " |" + rutina.tipoRutina

        binding.flechaAtras4.setOnClickListener {
            finish()
        }


        binding.btnFechaHora.setOnClickListener {
            val intent = Intent(this, RetarAmigoCalendario::class.java)
            intent.putExtra("datos_amigo", amigo)
            intent.putExtra("datos_rutina", rutina )
            startActivity(intent)
        }

        binding.flechaAtras4.setOnClickListener {
            finish()
        }
    }
}