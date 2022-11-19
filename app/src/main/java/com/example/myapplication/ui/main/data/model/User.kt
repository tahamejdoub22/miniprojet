package com.example.myapplication.ui.main.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: String,
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String,
    var role: List<String>,
    @SerializedName("password") var password: String
)
