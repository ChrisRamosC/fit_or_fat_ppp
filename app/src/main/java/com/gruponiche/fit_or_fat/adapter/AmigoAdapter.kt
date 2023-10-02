package com.gruponiche.fit_or_fat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.model.Amigo

class AmigoAdapter (private val amigoList:List<Amigo>, private val onClickListener: (Amigo) -> Unit) : RecyclerView.Adapter<AmigoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmigoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AmigoViewHolder(layoutInflater.inflate(R.layout.item_amigo, parent, false))
    }

    override fun onBindViewHolder(holder: AmigoViewHolder, position: Int) {
        val item = amigoList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = amigoList.size

}