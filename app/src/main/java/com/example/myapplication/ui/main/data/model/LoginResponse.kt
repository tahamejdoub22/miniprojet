package com.example.myapplication.ui.main.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("role") var role: String,
    @SerializedName("accessToken") var accessToken: String
)