package com.example.myapplication.data.retrofit

import com.example.myapplication.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIClient {
    @POST("auth/login")
    suspend fun signIn(
        @Body user:UserRequest

    ): Response<LoginResponse>
    @POST("auth/register")
    suspend fun singUp(
        @Body user:UserSignUpRequest
    ):Response<UserSignupResponse>
}