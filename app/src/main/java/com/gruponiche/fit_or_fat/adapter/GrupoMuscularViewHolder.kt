package com.gruponiche.fit_or_fat.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.model.GrupoMuscular

class GrupoMuscularViewHolder (view: View):RecyclerView.ViewHolder(view) {

    val grupoMuscularNombre = view.findViewById<TextView>(R.id.tvGrupoMuscular)
    val grupoMuscularImagen = view.findViewById<ImageView>(R.id.ivGrupoMuscular)

    fun render(grupoMuscularModel: GrupoMuscular){
        grupoMuscularNombre.text = grupoMuscularModel.nombre
        Glide.with(grupoMuscularImagen.context).load(grupoMuscularModel.imagen).into(grupoMuscularImagen)
    }

}