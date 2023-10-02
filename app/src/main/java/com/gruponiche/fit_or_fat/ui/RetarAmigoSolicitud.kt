package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.databinding.ActivityRetarAmigoSolicitudBinding
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import com.gruponiche.fit_or_fat.model.Amigo

class RetarAmigoSolicitud : AppCompatActivity() {
    private lateinit var binding: ActivityRetarAmigoSolicitudBinding
    private lateinit var amigo: Amigo
    private lateinit var rutina: RutinaResponse
    private lateinit var fecha: String
    private lateinit var hora: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetarAmigoSolicitudBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        amigo = (bundle?.getSerializable("datos_amigo") as? Amigo)!!
        rutina = (bundle.getSerializable("datos_rutina") as? RutinaResponse)!!
        fecha = bundle.getString("fecha")!!
        hora = bundle.getString("hora")!!

        binding.tvFecha.text = fecha
        Glide.with(binding.ivFotoAmigo.context).load(amigo.imgAmigo)
            .into(binding.ivFotoAmigo)
        binding.tvNombreAmigo.text = amigo.nombre
        binding.tvHora.text = hora

        binding.flechaAtras3.setOnClickListener {
            finish()
        }

        binding.btnAceptar.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }

    }
}