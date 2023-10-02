package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruponiche.fit_or_fat.adapter.AmigoAdapter
import com.gruponiche.fit_or_fat.databinding.ActivityRetarAmigoBinding
import com.gruponiche.fit_or_fat.io.OnAmigosLoadedListener
import com.gruponiche.fit_or_fat.model.Amigo
import com.gruponiche.fit_or_fat.model.AmigoProvider

class RetarAmigo : AppCompatActivity(), OnAmigosLoadedListener {

    private lateinit var binding: ActivityRetarAmigoBinding
    private lateinit var selectedRb: RadioButton
    private lateinit var amigosAdapter: AmigoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetarAmigoBinding.inflate(layoutInflater)


        obtenerAmigos()
//        initRecycleViewAmigo()

//        navegacion()

        selectedRb =
            binding.tipoRutinas.findViewById<RadioButton>(binding.tipoRutinas.checkedRadioButtonId)

        binding.tipoRutinas.setOnCheckedChangeListener { x, y ->
            selectedRb =
                binding.tipoRutinas.findViewById<RadioButton>(binding.tipoRutinas.checkedRadioButtonId)
        }

        binding.flechaAtras.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    fun initRecycleViewAmigo(amigos: List<Amigo>) {
        val manager = LinearLayoutManager(this)
        binding.recyclerAmigo.layoutManager = manager
        amigosAdapter = AmigoAdapter(amigos) { amigo ->
            onItemSelectd(amigo)
        }
        binding.recyclerAmigo.adapter = amigosAdapter
    }

    fun onItemSelectd(amigo: Amigo) {
        val intent = Intent(this, RetarAmigoBuscar::class.java)
        intent.putExtra("datos_amigo", amigo)
        intent.putExtra("tipo_rutina", selectedRb.text)
        startActivity(intent)
    }

    override fun onAmigosLoaded(amigos: List<Amigo>) {
        initRecycleViewAmigo(amigos)
        amigosAdapter.notifyDataSetChanged()
    }

    fun obtenerAmigos() {

        // Obtener una instancia de Shared Preferences
        val sharedPreferences3 = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        // Recuperar un valor entero almacenado
        val userId = sharedPreferences3.getInt("id_usuario", 7)
        Log.d("iduser", userId.toString())

        AmigoProvider.getAmigosbyUserID(userId, this)
    }
}