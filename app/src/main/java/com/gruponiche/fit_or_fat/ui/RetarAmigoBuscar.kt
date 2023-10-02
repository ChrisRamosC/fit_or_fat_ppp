package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruponiche.fit_or_fat.adapter.RutinaAdapter
import com.gruponiche.fit_or_fat.databinding.ActivityRetarAmigoBuscarBinding
import com.gruponiche.fit_or_fat.io.OnRutinasLoadedListener
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import com.gruponiche.fit_or_fat.model.Amigo
import com.gruponiche.fit_or_fat.model.RutinaProvider


class RetarAmigoBuscar : AppCompatActivity(), OnRutinasLoadedListener {

    private lateinit var binding: ActivityRetarAmigoBuscarBinding
//    private var rutinaMutableList: MutableList<RutinaResponse> =
//        RutinaProvider.listaRutina.toMutableList()

    private lateinit var rutinaMutableList: MutableList<RutinaResponse>

    private lateinit var rutinasAdapter: RutinaAdapter
    private val llmanager = LinearLayoutManager(this)
    private var estadoBoton = 1
    private lateinit var dato: Amigo
    private lateinit var tipo_rutina: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetarAmigoBuscarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // buscador
        buscadorRutinas()

        obtenerRutinas()



        binding.flechaAtras2.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        dato = (bundle?.getSerializable("datos_amigo") as? Amigo)!!
        tipo_rutina = bundle.getString("tipo_rutina")!!
        binding.textView12.text = tipo_rutina



        var fuera_casa = !tipo_rutina.equals("Rutinas de casa")

//        initRecycleViewRutina(fuera_casa)
    }


    fun buscador(rutinasFiltered: List<RutinaResponse>) {
        rutinasAdapter.updateRutinas(rutinasFiltered)
    }


    private fun buscadorRutinas() {
        binding.busquedaRutina.addTextChangedListener { userFilter ->
            Log.i("aris", userFilter.toString())
            val rutinasFiltered = rutinaMutableList.filter { rutina ->
                rutina.nombreRutina.lowercase().contains(userFilter.toString().lowercase())
            }
            buscador(rutinasFiltered)
        }
 }


    private fun initRecycleViewRutina(rutinas: List<RutinaResponse>) {
        binding.recyclerRutina.layoutManager = llmanager
        rutinasAdapter = RutinaAdapter(
            rutinas
        ) { rutina -> onItemSelected(rutina) }


        binding.recyclerRutina.adapter = rutinasAdapter
    }

    fun onItemSelected(rutina: RutinaResponse) {
        val intent = Intent(this, RetarAmigoSeleccion::class.java)
        intent.putExtra("datos_amigo", dato)
        intent.putExtra("datos_rutina", rutina )
        startActivity(intent)
    }
    override fun onRutinasLoaded(rutinas: List<RutinaResponse>) {
        initRecycleViewRutina(rutinas)
        rutinaMutableList = rutinas as MutableList<RutinaResponse>
        rutinasAdapter.notifyDataSetChanged()
    }

    fun obtenerRutinas(){
        RutinaProvider.getRutinas("","","", this)
    }

}

