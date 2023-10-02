package com.gruponiche.fit_or_fat.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.io.response.EjercicioResponse

class EjercicioAdapter(
    private val context: Context,
    private val ejercicioList: List<EjercicioResponse>,
    private val onClickListener: (EjercicioResponse) -> Unit
) : RecyclerView.Adapter<EjercicioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EjercicioViewHolder(layoutInflater.inflate(R.layout.item_ejercicio, parent, false))
    }

    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        val item = ejercicioList[position]
        holder.render(item, onClickListener)
        holder.itemView.setOnClickListener {
            showDialogInformacion(position)
        }
    }

    override fun getItemCount(): Int = ejercicioList.size

    private fun showDialogInformacion(position: Int) {

        val item = ejercicioList[position]
        val context = context

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_informacion, null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        view.findViewById<TextView>(R.id.txtNombreEjercicio).text = item.nombre_ejercicio
        view.findViewById<TextView>(R.id.txtDescripcionEjercicio).text = item.descripcion_ejercicio
        Glide.with(context).load(item.gif)
            .into(view.findViewById<ImageView>(R.id.imgImagenEjercicio))

        val dialog = builder.create()
        dialog.show()

        view.findViewById<ImageView>(R.id.btn_cerrar).setOnClickListener {
            dialog.dismiss()
        }

    }

}
