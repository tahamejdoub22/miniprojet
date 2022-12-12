package com.gmail.apigeoneer.miniprojets.onboarding.network


class ApiHelper(private val apiInterface: ApiInterface) {
    suspend fun getAllResult() = apiInterface.getAllResult()
}