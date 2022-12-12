package com.gmail.apigeoneer.miniprojets.onboarding.screens

import android.graphics.Movie
import com.gmail.apigeoneer.miniprojets.onboarding.model.MList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("api/Materiel/getAllMateriel")
    fun getAllMovies() : Call<MList>


    companion object {
        var retrofitService: RetrofitService? = null
    fun getInstance(): RetrofitService {
        if (retrofitService == null) {


            var httpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            var okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
            var retrofit: Retrofit = retrofit2.Retrofit.Builder()
                .baseUrl("https://backend-5y8uihs3w-tahamejdoub22.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            retrofitService = retrofit.create(RetrofitService::class.java)

        }
        return retrofitService!!
}}}
