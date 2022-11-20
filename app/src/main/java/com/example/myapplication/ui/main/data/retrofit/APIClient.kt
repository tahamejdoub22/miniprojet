package com.example.myapplication.ui.main.data.retrofit

import com.example.myapplication.ui.main.data.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIClient {
    @POST("api/auth/signin")
    suspend fun signIn(
        @Body user: UserRequest

    ): Response<LoginResponse>

    @POST("api/auth/signup")
    suspend fun singUp(
        @Body user: UserSignUpRequest
    ): Response<UserSignupResponse>

    @GET("Materials")
    suspend fun getMaterials(): Response<List<MaterialResponse>>
    @GET("entreprise")
    suspend fun getentreprise(): Response<List<entrepriseResponse>>
    companion object {
        operator fun invoke(): APIClient {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.176.97:3000")
                .build()
                .create(APIClient::class.java)

        }

    }}


