package com.example.recycleview.retrofit

import android.provider.ContactsContract
import com.example.recycleview.pojo.Mat
import com.example.recycleview.pojo.materielList
import com.example.recycleview.pojo.user
import retrofit2.Call
import retrofit2.http.GET

interface DemoRemoteService {
    @GET("api/users/profile")
    fun getProfile(): Call<user>
    @GET("api/Materiel/getAllMateriel/slider")
    fun getfavoriMateriel(): Call<Mat>
}