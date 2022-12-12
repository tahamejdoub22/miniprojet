package com.gmail.apigeoneer.miniprojets.onboarding.network

import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginRequest
import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.MList
import com.gmail.apigeoneer.miniprojets.onboarding.model.RecyclerList
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.lang.reflect.Type

interface UserApi {

    @POST("api/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
    //@GET("api/Materiel/getAllMateriel")
//suspend fun getDataromAPI(@Query("q") query: String):MList

}

