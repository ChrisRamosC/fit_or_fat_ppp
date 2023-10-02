package com.gruponiche.fit_or_fat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.model.GrupoMuscular

class GrupoMuscularAdapter(private val grupoMuscularList:List<GrupoMuscular>) : RecyclerView.Adapter<GrupoMuscularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrupoMuscularViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GrupoMuscularViewHolder(layoutInflater.inflate(R.layout.item_grupomuscular, parent, false))
    }

    override fun onBindViewHolder(holder: GrupoMuscularViewHolder, position: Int) {
        val item = grupoMuscularList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = grupoMuscularList.size
}