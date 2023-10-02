package com.gruponiche.fit_or_fat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.databinding.ActivityVisualizarProgresoBinding

class VisualizarProgreso : AppCompatActivity(){
    private lateinit var binding : ActivityVisualizarProgresoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizarProgresoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val boton_atras = binding.btnAtrasVisualizarProgreso
        boton_atras.setOnClickListener { onBackPressed() }

    }

}