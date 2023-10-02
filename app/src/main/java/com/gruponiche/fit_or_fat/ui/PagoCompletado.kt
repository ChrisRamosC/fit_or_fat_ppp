package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.databinding.ActivityPagoCompletadoBinding

class PagoCompletado : AppCompatActivity() {

    private lateinit var binding: ActivityPagoCompletadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagoCompletadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinalizar.setOnClickListener {
            goToPantallaInicial()
        }
    }

    private fun goToPantallaInicial() {
        val intent = Intent (this, MenuPrincipal::class.java)
        startActivity(intent)
    }

}