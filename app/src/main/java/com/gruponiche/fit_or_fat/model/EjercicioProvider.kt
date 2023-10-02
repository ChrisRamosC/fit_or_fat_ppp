package com.gruponiche.fit_or_fat.model

import com.gruponiche.fit_or_fat.io.Api2RutinasDeEjercicios
import com.gruponiche.fit_or_fat.io.OnEjerciciosLoadedListener
import com.gruponiche.fit_or_fat.io.response.EjercicioResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EjercicioProvider {
    companion object {
        private val api2RutinasDeEjercicios: Api2RutinasDeEjercicios by lazy {
            Api2RutinasDeEjercicios.create()
        }

        fun getEjercicios(id: Int, listener: OnEjerciciosLoadedListener) {
            val call = api2RutinasDeEjercicios.getBuscarEjercicioById(id)
            call.enqueue(object : Callback<List<EjercicioResponse>> {
                override fun onResponse(
                    call: Call<List<EjercicioResponse>>,
                    response: Response<List<EjercicioResponse>>
                ) {
                    val lista = response.body()!!
                    val ejercicios = mutableListOf<EjercicioResponse>()
                    for (u in lista) {
                        ejercicios.add(
                            EjercicioResponse(
                                u.nombre_ejercicio,
                                u.descripcion_ejercicio,
                                u.video,
                                u.gif,
                                u.grupo_muscular,
                                u.nivel_ejercicio,
                                u.repeticiones,
                                u.duracion_seg,
                                u.descanso_seg
                            )
                        )
                    }
                    listener.onEjerciciosLoaded(ejercicios)
                }

                override fun onFailure(call: Call<List<EjercicioResponse>>, t: Throwable) {
                    // Crea una lista de datos de prueba
                    val ejerciciosDePrueba = listOf(
                        EjercicioResponse(
                            nombre_ejercicio = "Flexiones",
                            descripcion_ejercicio = "Ejercicio para fortalecer el pecho y los brazos",
                            video = "https://www.example.com/video.mp4",
                            gif = "https://hips.hearstapps.com/hmg-prod/images/pushup-1525365829.gif?crop=1.00xw:0.501xh;0,0.266xh&resize=640:*",
                            grupo_muscular = "Pecho, brazos",
                            nivel_ejercicio = "Intermedio",
                            repeticiones = 10,
                            duracion_seg = "30",
                            descanso_seg = 10
                        )
                    )

                    // Devuelve los datos de prueba al listener
                    listener.onEjerciciosLoaded(ejerciciosDePrueba)
                }
            })
        }
    }
}
