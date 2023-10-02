package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.adapter.GrupoMuscularAdapter
import com.gruponiche.fit_or_fat.adapter.RutinaAdapter
import com.gruponiche.fit_or_fat.databinding.FragmentInicioBinding
import com.gruponiche.fit_or_fat.io.OnRutinasLoadedListener
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import com.gruponiche.fit_or_fat.model.GrupoMuscularProvider
import com.gruponiche.fit_or_fat.model.RutinaProvider


class InicioFragment : Fragment(R.layout.fragment_inicio), OnRutinasLoadedListener {
    private lateinit var binding: FragmentInicioBinding
    private lateinit var recyclerViewGrupoMuscular: RecyclerView
    private lateinit var recyclerViewCategorias: RecyclerView

//    private var rutinaMutableList: MutableList<RutinaResponse> =
//        RutinaProvider.listaRutina.toMutableList()
    private lateinit var rutinasAdapter: RutinaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_inicio, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtenerRutinas()

        recyclerViewGrupoMuscular = view.findViewById(R.id.recyclerGrupoMuscular)
        recyclerViewCategorias = view.findViewById(R.id.recyclerCategorias)
    }

    fun initRecycleViewRutina(rutinas: List<RutinaResponse>) {
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val layoutManager2 = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        recyclerViewGrupoMuscular.layoutManager = layoutManager
        recyclerViewGrupoMuscular.setHasFixedSize(true)
        recyclerViewGrupoMuscular.adapter = GrupoMuscularAdapter(GrupoMuscularProvider.grupoMuscularList)

        recyclerViewCategorias.layoutManager = layoutManager2
        recyclerViewCategorias.setHasFixedSize(true)

        rutinasAdapter = RutinaAdapter(rutinas) {rutina->
            onItemSelected(rutina)
        }
        recyclerViewCategorias.adapter = rutinasAdapter
    }

    fun onItemSelected(rutina: RutinaResponse) {
        val intent = Intent(context, ComenzarRutina::class.java)
        intent.putExtra("datos_rutina", rutina)
        startActivity(intent)
    }

    override fun onRutinasLoaded(rutinas: List<RutinaResponse>) {
        initRecycleViewRutina(rutinas)
        rutinasAdapter.notifyDataSetChanged()
    }

    fun obtenerRutinas(){
        RutinaProvider.getRutinas("","","", this)
    }

}