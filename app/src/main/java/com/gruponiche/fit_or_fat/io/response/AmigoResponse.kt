package com.gruponiche.fit_or_fat.io.response

data class AmigoResponse (
    val codigo:Int,
    val nombre:String,
    val telefono: String,
    val correo:String,
    val es_Premium:Boolean,
    val imagen:String,
    val codigo_usuario:Int
)