package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.adapter.RutinaAdapter
import com.gruponiche.fit_or_fat.databinding.ActivityBuscarRutinaBinding
import com.gruponiche.fit_or_fat.io.OnRutinasLoadedListener
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import com.gruponiche.fit_or_fat.model.RutinaProvider
import com.google.android.material.bottomnavigation.BottomNavigationView


class BuscarRutina : AppCompatActivity(), OnRutinasLoadedListener {

    private lateinit var binding: ActivityBuscarRutinaBinding

    private lateinit var rutinasAdapter: RutinaAdapter

    private lateinit var listaRutinas: MutableList<RutinaResponse>

    private lateinit var rutinasFiltered: List<RutinaResponse>


    private val llmanager = LinearLayoutManager(this)
    private var estadoBoton = 1
    private var principiante = false
    private var intermedio = false
    private var avanzando = false
    private var tipo_rutina: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarRutinaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // buscador
        buscadorRutinas()


        // Filtros de rutinas
        menuFiltros()

        obtenerRutinas()
        //initRecycleView()

        // logica barra de navegacion
        navegacion()

        // CHECKBOX FILTROS
        binding.cbPrincipiante.setOnCheckedChangeListener { compoundButton, isChecked ->
            principiante = isChecked
            filtrar()
        }

        binding.cbIntermedio.setOnCheckedChangeListener { compoundButton, isChecked ->
            intermedio = isChecked
            filtrar()
        }

        binding.cbAvanzado.setOnCheckedChangeListener { compoundButton, isChecked ->
            avanzando = isChecked
            filtrar()
        }

        // Obtiene una referencia al botón en el layout
        val button: ImageButton = binding.botonTipoRutina

        button.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                tipo_rutina = item.title.toString()
                binding.etTipoRutina.hint = tipo_rutina
                tipo_rutina = tipo_rutina.lowercase()
                if (tipo_rutina.equals("de todo tipo")) tipo_rutina = ""
                val rutinasFiltered = listaRutinas.filter { rutina ->
                    rutina.tipoRutina.contains(tipo_rutina)
                }
                buscador(rutinasFiltered)
                when (item.itemId) {
                    else -> true
                }

            }


            // Infla el menú desde un archivo de recursos de menú XML
            popupMenu.inflate(R.menu.menu_popup)

            // Muestra el menú en pantalla
            popupMenu.show()

        }
    }

    fun filtrar() {
        var rutinasAuxPrincipiante: List<RutinaResponse>
        var rutinasAuxIntermedio: List<RutinaResponse>
        var rutinasAuxAvanzado: List<RutinaResponse>

        if (principiante) {
            rutinasAuxPrincipiante = listaRutinas.filter { rutina ->
                rutina.nivelRutina.contains("principiante")
            }
        } else {
            rutinasAuxPrincipiante = emptyList()
        }

        if (intermedio) {
            rutinasAuxIntermedio = listaRutinas.filter { rutina ->
                rutina.nivelRutina.contains("intermedio")
            }
        } else {
            rutinasAuxIntermedio = emptyList()
        }

        if (intermedio) {
            rutinasAuxAvanzado = listaRutinas.filter { rutina ->
                rutina.nivelRutina.contains("avanzado")
            }
        } else {
            rutinasAuxAvanzado = emptyList()
        }

        rutinasFiltered = rutinasAuxPrincipiante + rutinasAuxIntermedio + rutinasAuxAvanzado

        if (rutinasFiltered.isEmpty()) rutinasFiltered = listaRutinas

        buscador(rutinasFiltered)
    }

    fun buscador(rutinasFiltered: List<RutinaResponse>) {
        rutinasAdapter.updateRutinas(rutinasFiltered)
    }

    private fun buscadorRutinas() {
        binding.busquedaRutina.addTextChangedListener { userFilter ->
            val rutinasFiltered = listaRutinas.filter { rutina ->
                rutina.nombreRutina.lowercase().contains(userFilter.toString().lowercase())
            }
            buscador(rutinasFiltered)
        }
    }

    private fun menuFiltros() {
        binding.botonFiltros.setOnClickListener {

            // menu de
            if (estadoBoton == 1) {
                binding.layoutFiltros.visibility = android.view.View.VISIBLE
                binding.botonFiltros.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
                estadoBoton = 0

            } else {
                binding.layoutFiltros.visibility = android.view.View.GONE
                binding.botonFiltros.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                estadoBoton = 1
            }

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
        val intent = Intent(this, ComenzarRutina::class.java)
        intent.putExtra("datos_rutina", rutina)
        startActivity(intent)
    }


    fun navegacion() {
        // Navegacion barra inferior
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.buscarRutina
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuPrincipal -> {
                    startActivity(Intent(applicationContext, MenuPrincipal::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.buscarRutina -> true
                R.id.graficas -> {
                    startActivity(Intent(applicationContext, GraficaReportes::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.perfil -> {
                    startActivity(Intent(applicationContext, PerfilUsuario::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }

        }
    }

    override fun onRutinasLoaded(rutinas: List<RutinaResponse>) {
        initRecycleViewRutina(rutinas)
        listaRutinas = rutinas as MutableList<RutinaResponse>
        rutinasAdapter.notifyDataSetChanged()
    }

    fun obtenerRutinas() {
        RutinaProvider.getRutinas("", "", "", this)
    }

}