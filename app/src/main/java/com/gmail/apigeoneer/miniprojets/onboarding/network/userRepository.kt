package com.gmail.apigeoneer.miniprojets.onboarding.network

import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginRequest
import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginResponse
import com.gmail.apigeoneer.miniprojets.onboarding.model.RegisterRequest
import com.gmail.apigeoneer.miniprojets.onboarding.model.RegisterResponse
import retrofit2.Response

class userRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse>? {
        return  UserApi.getApi()?.registerUser(registerRequest = registerRequest)
    }
}