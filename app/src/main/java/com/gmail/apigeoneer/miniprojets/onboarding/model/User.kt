package com.gmail.apigeoneer.miniprojets.onboarding.model

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("email") var email: String,
    @SerializedName("username") var username:String,


    )
