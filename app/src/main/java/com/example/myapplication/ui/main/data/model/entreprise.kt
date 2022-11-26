package com.example.myapplication.ui.main.data.model

import com.google.gson.annotations.SerializedName

data class entreprise(

    @SerializedName("id")
    val id: String,
    @SerializedName("name")

    val name:String,
    @SerializedName("Photo")

    val Photo: String,
    @SerializedName("address")

    val address:String,
    @SerializedName("distance")

    val distance:String,
    @SerializedName("time")

    val time:String




)
