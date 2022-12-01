package com.example.myapplication.ui.main.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    fun getInstance(): Retrofit {
        var httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        var okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        var retrofit: Retrofit = retrofit2.Retrofit.Builder().baseUrl("http://192.168.1.191:3000")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
        return retrofit
    }

}
