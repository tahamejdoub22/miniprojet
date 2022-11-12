package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("email") var email: String,
    var role: String,
    @SerializedName("password") var password: String
)
