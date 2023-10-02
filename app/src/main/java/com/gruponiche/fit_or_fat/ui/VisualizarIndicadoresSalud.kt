package com.gruponiche.fit_or_fat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.databinding.ActivityVisualizarIndicadoresSaludBinding

class VisualizarIndicadoresSalud : AppCompatActivity() {

    private lateinit var binding : ActivityVisualizarIndicadoresSaludBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizarIndicadoresSaludBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val boton_atras = binding.btnAtrasVisualizarIndicadores
        boton_atras.setOnClickListener { onBackPressed() }
    }

}