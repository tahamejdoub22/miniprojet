package com.example.myapplication.ui.main.data.retrofit

import com.example.myapplication.ui.main.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIClient {
    @POST("auth/login")
    suspend fun signIn(
        @Body user: UserRequest

    ): Response<LoginResponse>

    @POST("api/auth/signup")
    suspend fun singUp(
        @Body user: UserSignUpRequest
    ): Response<UserSignupResponse>

    @GET("auth/getAll")
     fun getMaterials(): Call<MList>
    @GET("auth/getAllentreprise")
    fun getentreprise(): Call<EList>



    }


