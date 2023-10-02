package com.gruponiche.fit_or_fat.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.databinding.ActivityBarraNavegacionBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BarraNavegacion : AppCompatActivity() {

    private lateinit var binding: ActivityBarraNavegacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarraNavegacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}