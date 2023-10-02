package com.gruponiche.fit_or_fat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.databinding.ItemAmigoBinding
import com.gruponiche.fit_or_fat.model.Amigo

class AmigoViewHolder (view: View) : RecyclerView.ViewHolder(view){
    val binding = ItemAmigoBinding.bind(view)

    fun render(amigoModel: Amigo, onClickListener: (Amigo) -> Unit) {
        binding.tvNombreAmigo.text = amigoModel.nombre
        Glide.with(binding.ivFotoAmigo.context).load(amigoModel.imgAmigo).into(binding.ivFotoAmigo)
        itemView.setOnClickListener{
            onClickListener(amigoModel)
        }
    }
}