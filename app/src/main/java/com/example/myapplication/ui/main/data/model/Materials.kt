package com.example.myapplication.ui.main.data.model

import com.google.gson.annotations.SerializedName

data class Materials(

    @SerializedName("id")
    val id: String,
    @SerializedName("materialImage")
    val materialImage: String,
    @SerializedName("materialName")
    val materialName:String,
    @SerializedName("type")
    val type: List<String>,

)
