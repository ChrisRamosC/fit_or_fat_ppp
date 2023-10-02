package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gruponiche.fit_or_fat.databinding.ActivityConviertetePremiumBinding

class ConviertetePremium : AppCompatActivity() {

    private lateinit var binding: ActivityConviertetePremiumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConviertetePremiumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.flechaAtras14.setOnClickListener {
            finish()
        }

        binding.btnSucribeteAhora.setOnClickListener {
            goToPagoPaypal()
        }
    }

    private fun goToPagoPaypal() {
        val intent = Intent (this, PagoPaypal::class.java)
        startActivity(intent)
    }

}