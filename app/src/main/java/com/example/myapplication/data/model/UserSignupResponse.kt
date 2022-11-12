package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class UserSignupResponse (
    @SerializedName("accessToken") var accessToken: String
        )