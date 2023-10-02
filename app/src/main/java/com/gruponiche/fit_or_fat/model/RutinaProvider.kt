package com.gruponiche.fit_or_fat.model

import android.util.Log
import com.gruponiche.fit_or_fat.io.Api2RutinasDeEjercicios
import com.gruponiche.fit_or_fat.io.OnRutinasLoadedListener
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RutinaProvider {
    companion object {
        private val apiServiceHu10: Api2RutinasDeEjercicios by lazy {
            Api2RutinasDeEjercicios.create()
        }

        fun getRutinas(frase: String, nivel: String, tipo: String, listener: OnRutinasLoadedListener) {
            val call = apiServiceHu10.getBuscarRutina(frase, nivel, tipo)
            call.enqueue(object : Callback<List<RutinaResponse>> {
                override fun onResponse(call: Call<List<RutinaResponse>>, response: Response<List<RutinaResponse>>) {
                    val lista = response.body()!!
                    Log.d("prueba", lista.toString())
                    val rutinas = mutableListOf<RutinaResponse>()
                    for (u in lista) {
                        rutinas.add(
                            RutinaResponse(
                                u.idRutinaEjercicio,
                                u.nombreRutina,
                                u.duracionRutina,
                                u.descripcion,
                                u.linkImagen,
                                u.nivelRutina,
                                u.tipoRutina,
                                u.fueraDeCasa
                            )
                        )
                    }
                    listener.onRutinasLoaded(rutinas)
                }

                override fun onFailure(call: Call<List<RutinaResponse>>, t: Throwable) {
                    Log.d("prueba", "onFailure: ${t.message}")
                }
            })
        }


    }

}