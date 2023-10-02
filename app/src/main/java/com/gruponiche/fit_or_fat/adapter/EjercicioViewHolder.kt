package com.gruponiche.fit_or_fat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.databinding.ItemEjercicioBinding
import com.gruponiche.fit_or_fat.io.response.EjercicioResponse

class EjercicioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemEjercicioBinding.bind(view)

    fun render(ejercicioModel: EjercicioResponse, onClickListener: (EjercicioResponse) -> Unit) {
        binding.tvEjercicioNombre.text = ejercicioModel.nombre_ejercicio
        binding.tvEjercicioCantidad.text = ejercicioModel.repeticiones.toString()
        Glide.with(binding.ivEjercicio.context).load(ejercicioModel.gif).into(binding.ivEjercicio)
        itemView.setOnClickListener{
            onClickListener(ejercicioModel)
        }
    }
}