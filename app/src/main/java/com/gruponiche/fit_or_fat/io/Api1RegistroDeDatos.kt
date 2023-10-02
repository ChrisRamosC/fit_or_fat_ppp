package com.gruponiche.fit_or_fat.io

import com.gruponiche.fit_or_fat.io.response.*
import com.gruponiche.fit_or_fat.model.DatosUsuario
import com.gruponiche.fit_or_fat.io.response.CreateResponse
import com.gruponiche.fit_or_fat.io.response.EnviarCorreoResponse
import com.gruponiche.fit_or_fat.io.response.LoginResponse
import com.gruponiche.fit_or_fat.io.response.RegistrarDatosResponse
import com.gruponiche.fit_or_fat.io.response.RestablecerContrasenaResponse
import com.gruponiche.fit_or_fat.io.response.VerificarCodigoResponse
import com.gruponiche.fit_or_fat.model.Usuario
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface Api1RegistroDeDatos {
    @POST(value="/ux-registrar-datos/fitorfat/servicio-al-cliente/v1/autenticacion/iniciar-sesion")
    fun postLogin(@Query(value="correo") correo:String, @Query(value="contrasena") contrasena:String):
            Call<LoginResponse>

    @POST("/ux-registrar-datos/fitorfat/servicio-al-cliente/v1/usuario/registrar-usuarios")
    fun postCreateUsuario(
        @Body usuario: Usuario,

        ): Call<CreateResponse>
    @POST("/ux-registrar-datos/fitorfat/servicio-al-cliente/v1/usuario/solicitar-codigo-recuperacion")
    fun postEnviarCorreo(
        @Query(value = "correo") correo: String,
    ): Call<EnviarCorreoResponse>

    @POST("/ux-registrar-datos/fitorfat/servicio-al-cliente/v1/usuario/verificar-codigo-recuperacion")
    fun postVerificarCodigo(
        @Query(value = "codigo_verificacion") codigo_verificacion: String
    ): Call<VerificarCodigoResponse>

    @POST("/ux-registrar-datos/fitorfat/servicio-al-cliente/v1/usuario/reestablecer-password")
    fun postRestablecerContrasena(
        @Query(value = "contrasena") contrasena: String,@Query(value = "codigo_verificacion") codigo_verificacion: String
    ): Call<RestablecerContrasenaResponse>

    @POST("/ux-registrar-datos/fitorfat/servicio-al-cliente/v1/detalles-usuario/registrar-detalles-usuarios/{id}")
    fun postRegistrarDatos(
        @Path("id") id: Int, @Body datosUsuario: DatosUsuario,

        ): Call<RegistrarDatosResponse>



    companion object Factory{
        private const val BASE_URL="http://10.0.2.2:8080/"
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        fun create(): Api1RegistroDeDatos {
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Api1RegistroDeDatos::class.java)
        }
    }
}