package com.gmail.apigeoneer.miniprojets.onboarding.network

class ProfileRepository(private val apiHelper: ApiHelper)  {
    suspend fun getUserProfile() = apiHelper.getAllResult()
}