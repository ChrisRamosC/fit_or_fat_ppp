package com.gruponiche.fit_or_fat.io

import com.gruponiche.fit_or_fat.io.response.EjercicioResponse
import com.gruponiche.fit_or_fat.io.response.RutinaResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Api2RutinasDeEjercicios {

    // http://hu18.8dccee27f877490f8e83.westeurope.aksapp.io/api/hu18/v1/ejercicio/comenzar/5
    @GET(value = "/ux-rutina-ejercicios/fitorfat/servicio-al-cliente/v1/comenzar/{id}")
    fun getBuscarEjercicioById(@Path("id") id: Int,
    ): Call<List<EjercicioResponse>>

    @GET(value = "/ux-rutina-ejercicios/fitorfat/servicio-al-cliente/v1/buscar")
    fun getBuscarRutina(@Query("frase") frase: String,
                        @Query("nivel") nivel: String,
                        @Query("tipo") tipo: String
    ): Call<List<RutinaResponse>>

    companion object Factory {
        private const val BASE_URL = "http://10.0.2.2:8081/"
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        fun create(): Api2RutinasDeEjercicios {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Api2RutinasDeEjercicios::class.java)
        }
    }
}