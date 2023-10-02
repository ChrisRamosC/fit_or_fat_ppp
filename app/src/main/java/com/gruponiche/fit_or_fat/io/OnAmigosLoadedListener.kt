package com.gruponiche.fit_or_fat.io

import com.gruponiche.fit_or_fat.model.Amigo

interface OnAmigosLoadedListener {
    fun onAmigosLoaded(amigos: List<Amigo>)
}