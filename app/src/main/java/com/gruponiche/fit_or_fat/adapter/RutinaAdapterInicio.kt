package com.gruponiche.fit_or_fat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.io.response.RutinaResponse


class RutinaAdapterInicio(
    private var rutinaList: List<RutinaResponse>,
    private val onClickListener: (RutinaResponse) -> Unit
) : RecyclerView.Adapter<RutinaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RutinaViewHolder(layoutInflater.inflate(R.layout.item_rutina_inicio, parent, false))
    }

    override fun onBindViewHolder(holder: RutinaViewHolder, position: Int) {
        val item = rutinaList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = rutinaList.size

    fun updateRutinas(rutinaList: List<RutinaResponse>) {
        this.rutinaList = rutinaList
        notifyDataSetChanged()
    }
}