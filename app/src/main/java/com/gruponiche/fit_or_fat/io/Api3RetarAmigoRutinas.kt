package com.gruponiche.fit_or_fat.io

import com.gruponiche.fit_or_fat.io.response.*
import com.gruponiche.fit_or_fat.io.response.AmigoResponse
import com.gruponiche.fit_or_fat.io.response.CompetenciaResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface Api3RetarAmigoRutinas {

    @POST(value="/ux-retar-amigo-rutinas/fitorfat/servicio-al-cliente/v1/competencias/registrar-rutina")
    fun postRegistrarCompetencia(@Query(value="invitado") invitado:Int,
                                 @Query(value="rutina_ejercicio_id") rutina_ejercicio_id:Int,
                                 @Query(value="usuario_codigo") usuario_codigo:Int
    ): Call<CompetenciaResponse>

    @GET(value="/ux-retar-amigo-rutinas/fitorfat/servicio-al-cliente/v1/amigos/consultar-amigos/{id}")
    fun getAmigosUsuario(@Path("id") id: Int): Call<List<AmigoResponse>>

    companion object Factory{
        private const val BASE_URL="http://10.0.2.2:8082/"
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        fun create(): Api3RetarAmigoRutinas {
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Api3RetarAmigoRutinas::class.java)
        }
    }
}