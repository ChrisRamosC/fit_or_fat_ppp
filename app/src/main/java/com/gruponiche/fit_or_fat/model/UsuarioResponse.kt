package com.gruponiche.fit_or_fat.model

/*

"codigo": 2,
    "nombre": "Axel",
    "telefono": "990858103",
    "correo": "correo1@correo.com",
    "contrasena": "$2a$10$D6CqgBpw.KKKt7Y5eZr51ebvdNrGLlEP6G6M3Up1MBnWDDKUU4TdW",
 */
data class UsuarioResponse(
    val codigo:Int,
    val nombre:String,
    val telefono:String,
    val correo:String,
    val contrasena:String,
    val es_autenticado:Boolean
)
