package com.gruponiche.fit_or_fat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.databinding.ItemRutinaBinding
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import java.util.*

class RutinaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemRutinaBinding.bind(view)

    fun render(rutinaModel: RutinaResponse, onClickListener: (RutinaResponse) -> Unit) {
        binding.tvRutinaNombre.text = rutinaModel.nombreRutina.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
        binding.tvRutinaInfo.text = "| " + rutinaModel.nivelRutina.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        } + " | " + rutinaModel.tipoRutina.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
        Glide.with(binding.ivRutina.context).load(rutinaModel.linkImagen).into(binding.ivRutina)
        itemView.setOnClickListener {
            onClickListener(rutinaModel)
        }
    }



}