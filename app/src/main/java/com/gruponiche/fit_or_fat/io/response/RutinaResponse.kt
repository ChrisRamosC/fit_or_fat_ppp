package com.gruponiche.fit_or_fat.io.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RutinaResponse(
    @SerializedName("id_rutina_ejercicio")
    val idRutinaEjercicio: Int,
    @SerializedName("nombre_rutina")
    val nombreRutina: String,
    @SerializedName("duracion_rutina")
    val duracionRutina: Double,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("linkImagen")
    val linkImagen: String,
    @SerializedName("nivel_rutina")
    val nivelRutina: String,
    @SerializedName("tipo_rutina")
    val tipoRutina: String,
    @SerializedName("fuera_de_casa")
    val fueraDeCasa: Boolean
) : Serializable