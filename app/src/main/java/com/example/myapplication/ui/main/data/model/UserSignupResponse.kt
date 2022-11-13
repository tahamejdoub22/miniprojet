package com.example.myapplication.ui.main.data.model

import com.google.gson.annotations.SerializedName

data class UserSignupResponse (
    @SerializedName("accessToken") var accessToken: String
        )