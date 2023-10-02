package com.gruponiche.fit_or_fat.io.response

import java.io.Serializable

data class EjercicioResponse(
    val nombre_ejercicio: String,
    val descripcion_ejercicio: String,
    val video: String,
    val gif: String,
    val grupo_muscular: String,
    val nivel_ejercicio: String,
    val repeticiones: Int,
    val duracion_seg: String,
    val descanso_seg: Int
) : Serializable

