package com.gmail.apigeoneer.miniprojets.onboarding.model

import com.google.gson.annotations.SerializedName

data class Materials(
    @SerializedName("success")
    val success: Boolean,


    @SerializedName("id")
    val id: String,
    @SerializedName("materialImage")
    val materialImage: String,
    @SerializedName("materialName")
    val materialName:String,
    @SerializedName("type")
    val type: List<String>,

)
