package com.gmail.apigeoneer.miniprojets.onboarding.network

import com.gmail.apigeoneer.miniprojets.onboarding.model.LoginResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/users/profile")
    suspend fun getAllResult(): LoginResponse
    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}