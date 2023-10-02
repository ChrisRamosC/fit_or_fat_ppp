package com.gruponiche.fit_or_fat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gruponiche.fit_or_fat.databinding.ActivityRutinaFinalizadaBinding
import com.gruponiche.fit_or_fat.ui.MenuPrincipal

//import com.enanitosverdes.fit_or_fat.ui.Pantalla_principal

class RutinaFinalizada : AppCompatActivity() {
    private lateinit var binding: ActivityRutinaFinalizadaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutinaFinalizadaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent.extras
        val nombreRutina = bundle?.getString("nombre_rutina")
        val cantidadEjercicios = bundle?.getInt("cantidad")

        binding.tvNombreRutina.text = nombreRutina

        binding.tvDescripcionFinal.text = nombreRutina + "\n Completada!"

        binding.tvCantidadFinalizada.text =
            cantidadEjercicios.toString() + " ejercicios finalizados"
        if (cantidadEjercicios != null) {
            binding.tvCalorias.text = (cantidadEjercicios * 10.5).toString() + " kcal quemadas"
        }
        binding.tvTiempoEstimado.text = cantidadEjercicios.toString() + " minutos"


        binding.btnPrincipal.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }


    }


}