package com.gruponiche.fit_or_fat.model

/*

"codigo": 2,
    "nombre": "Axel",
    "telefono": "990858103",
    "correo": "correo1@correo.com",
    "contrasena": "$2a$10$D6CqgBpw.KKKt7Y5eZr51ebvdNrGLlEP6G6M3Up1MBnWDDKUU4TdW",
 */
data class DatosUsuario(
    val edad:String,
    val sexo:String,
    val peso:String,
    val talla:String,
    val nivel_actividad:String,
    val zona_objetivo:String,
    val objetivo:String
)
