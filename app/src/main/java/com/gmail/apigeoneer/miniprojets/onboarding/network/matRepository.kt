package com.gmail.apigeoneer.miniprojets.onboarding.network

import com.gmail.apigeoneer.miniprojets.onboarding.screens.RetrofitService


class matRepository(private val retrofitService: RetrofitService) {
    fun getAllMovies() = retrofitService.getAllMovies()
}
