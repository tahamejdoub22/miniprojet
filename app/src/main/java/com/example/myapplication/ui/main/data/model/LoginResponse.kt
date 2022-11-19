package com.example.myapplication.ui.main.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("role") var role: List<String>,
    @SerializedName("token") var token: String,
    @SerializedName("id") var id:String,
    @SerializedName("username") var username:String,
    @SerializedName("email") var email:String


)