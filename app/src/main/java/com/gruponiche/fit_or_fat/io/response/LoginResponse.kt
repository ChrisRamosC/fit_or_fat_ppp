package com.gruponiche.fit_or_fat.io.response

import com.gruponiche.fit_or_fat.model.UsuarioResponse

data class LoginResponse(
    val usuario: UsuarioResponse,
    val token:String,
    val estado:Int
)
