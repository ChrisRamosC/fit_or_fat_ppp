package com.gruponiche.fit_or_fat.io.response

data class CompetenciaResponse (
    val idCompetencia_Rutina:Int,
    val invitado:Int,
    val fecha_inicio:String,
    val estadoSolicitud:String,
    val ganador:Int,
    val rutina_ejercicio_id:Int,
    val usuario_codigo:Int
)