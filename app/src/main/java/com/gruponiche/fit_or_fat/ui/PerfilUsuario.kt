package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gruponiche.fit_or_fat.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class PerfilUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        navegacion()
    }


    fun navegacion() {
        // Navegacion barra inferior
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.perfil

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuPrincipal -> {
                    startActivity(Intent(applicationContext, MenuPrincipal::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.buscarRutina -> {
                    startActivity(Intent(applicationContext, BuscarRutina::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.graficas-> {
                    startActivity(Intent(applicationContext, GraficaReportes::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.perfil -> true
                else -> false
            }

        }
    }
}