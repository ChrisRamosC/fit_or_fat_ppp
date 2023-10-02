package com.gruponiche.fit_or_fat.io

import com.gruponiche.fit_or_fat.io.response.EjercicioResponse

interface OnEjerciciosLoadedListener {
    fun onEjerciciosLoaded(ejercicios: List<EjercicioResponse>)
}