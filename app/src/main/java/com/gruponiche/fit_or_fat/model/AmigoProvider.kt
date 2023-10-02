package com.gruponiche.fit_or_fat.model

import com.gruponiche.fit_or_fat.io.Api3RetarAmigoRutinas
import com.gruponiche.fit_or_fat.io.OnAmigosLoadedListener
import com.gruponiche.fit_or_fat.io.response.AmigoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AmigoProvider {
    companion object {
        private val api3RetarAmigoRutinas: Api3RetarAmigoRutinas by lazy {
            Api3RetarAmigoRutinas.create()
        }

        fun getAmigosbyUserID(id: Int, listener: OnAmigosLoadedListener) {
            val call = api3RetarAmigoRutinas.getAmigosUsuario(id)
            call.enqueue(object : Callback<List<AmigoResponse>> {
                override fun onResponse(
                    call: Call<List<AmigoResponse>>,
                    response: Response<List<AmigoResponse>>
                ) {
                    val lista = response.body()!!
                    val amigos = mutableListOf<Amigo>()
                    for (u in lista) {
                        if (u.imagen != null && u.imagen != "")
                            amigos.add(Amigo(u.nombre, u.imagen))
                        else
                            amigos.add(Amigo(u.nombre))
                    }
                    listener.onAmigosLoaded(amigos)
                }
                override fun onFailure(call: Call<List<AmigoResponse>>, t: Throwable) {
                    // notificar al código principal de que hubo un error
                }
            })
        }
    }
}