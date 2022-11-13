package com.example.myapplication.ui.main.data.retrofit

import com.example.myapplication.ui.main.data.model.LoginResponse
import com.example.myapplication.ui.main.data.model.UserRequest
import com.example.myapplication.ui.main.data.model.UserSignUpRequest
import com.example.myapplication.ui.main.data.model.UserSignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIClient {
    @POST("auth/login")
    suspend fun signIn(
        @Body user: UserRequest

    ): Response<LoginResponse>
    @POST("auth/register")
    suspend fun singUp(
        @Body user: UserSignUpRequest
    ):Response<UserSignupResponse>
}