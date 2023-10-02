package com.gruponiche.fit_or_fat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.databinding.ActivityVisualizarEstadisticasBinding

class VisualizarEstadisticas : AppCompatActivity() {
    private lateinit var binding : ActivityVisualizarEstadisticasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizarEstadisticasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val boton_atras = binding.btnAtrasVisualizarEstadisticas
        boton_atras.setOnClickListener { onBackPressed() }

        val semanaFragment = EstadisticasSemanaFragment()
        val mesesFragment = EstadisticasMesesFragment()
        val aniosFragment = EstadisticasAniosFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerViewVisualizarEstadisticas, semanaFragment)
            commit()
        }
        binding.btnSemana.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerViewVisualizarEstadisticas, semanaFragment)
                commit()
            }
        }
        binding.btnMes.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerViewVisualizarEstadisticas, mesesFragment)
                commit()
            }
        }
        binding.btnAnio.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerViewVisualizarEstadisticas, aniosFragment)
                commit()
            }
        }
    }
}