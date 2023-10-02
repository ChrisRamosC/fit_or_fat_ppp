package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.adapter.EjercicioAdapter
import com.gruponiche.fit_or_fat.databinding.ActivityComenzarRutinaBinding
import com.gruponiche.fit_or_fat.io.OnEjerciciosLoadedListener
import com.gruponiche.fit_or_fat.io.response.EjercicioResponse
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import com.gruponiche.fit_or_fat.model.EjercicioProvider
import java.io.Serializable
import java.util.*

class ComenzarRutina : AppCompatActivity(), OnEjerciciosLoadedListener {
    private lateinit var binding: ActivityComenzarRutinaBinding
    private lateinit var dato: RutinaResponse
    private val llmanager = LinearLayoutManager(this)
    private lateinit var ejerciciosAdapter: EjercicioAdapter

    private lateinit var lista_ejercicios: List<EjercicioResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComenzarRutinaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.flechaAtras.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        dato = (bundle?.getSerializable("datos_rutina") as? RutinaResponse)!!
        if (dato != null) {
//            Toast.makeText(this, dato.descripcion, Toast.LENGTH_SHORT).show()
            Glide.with(binding.ivRutinaEjercicio.context).load(dato.linkImagen).into(binding.ivRutinaEjercicio)
            binding.tvDescripcion.text = dato.descripcion
            binding.tvNombreRutina.text = dato.nombreRutina
            binding.tvInfoRutina.text =
                dato.nivelRutina.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } + ": " + dato.duracionRutina.toInt()
                    .toString() + " ejercicios - " + dato.duracionRutina.toInt()
                    .toString() + " min"

        }

        obtenerEjercicios(dato.idRutinaEjercicio)

        binding.btnComenzarRutina.setOnClickListener {
            val intent = Intent(this, ComenzarRutina2::class.java)
         intent.putExtra("lista_ejercicios",  lista_ejercicios as Serializable)
            intent.putExtra("nombre_rutina", dato.nombreRutina)
            intent.putExtra("actual", 0)
            startActivity(intent)
        }
    }

    fun initRecycleViewEjercicio(ejercicios: List<EjercicioResponse>) {
        binding.recyclerEjercicio.layoutManager = llmanager
        ejerciciosAdapter = EjercicioAdapter(this, ejercicios) { ejercicio ->
            onItemSelectd(
                ejercicio
            )
        }
        binding.recyclerEjercicio.adapter = ejerciciosAdapter

    }


    fun onItemSelectd(ejercicio: EjercicioResponse) {
//        Toast.makeText(this, ejercicio.nombre_ing, Toast.LENGTH_SHORT).show()
    }

    override fun onEjerciciosLoaded(ejercicios: List<EjercicioResponse>) {
        initRecycleViewEjercicio(ejercicios)
        lista_ejercicios = ejercicios
        ejerciciosAdapter.notifyDataSetChanged()
    }

    fun obtenerEjercicios(id: Int){
        EjercicioProvider.getEjercicios(id, this)
    }
}