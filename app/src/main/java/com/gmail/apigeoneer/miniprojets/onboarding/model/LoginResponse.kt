package com.gmail.apigeoneer.miniprojets.onboarding.model

import com.google.gson.annotations.SerializedName




data class LoginResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
) {
    data class Data(
        @SerializedName("Email")
        var email: String,
        @SerializedName("id")
        var id: String,
        @SerializedName("Id")
        var id2: Int,
        @SerializedName("avatar")
        var avatar: String,
        @SerializedName("Name")
        var name: String,
        @SerializedName("Token")
        var token: String
    )
}
