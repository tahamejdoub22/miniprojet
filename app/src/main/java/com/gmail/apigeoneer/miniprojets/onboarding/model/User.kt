package com.gmail.apigeoneer.miniprojets.onboarding.model

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("email") var email: String,
    @SerializedName("id") var id:String,
    @SerializedName("name") var name:String,
    @SerializedName("avatar") var avatar:String,




    )
