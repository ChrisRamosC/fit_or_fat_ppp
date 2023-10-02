package com.gruponiche.fit_or_fat.io

import com.gruponiche.fit_or_fat.io.response.RutinaResponse

interface OnRutinasLoadedListener {
    fun onRutinasLoaded(rutinas: List<RutinaResponse>)
}