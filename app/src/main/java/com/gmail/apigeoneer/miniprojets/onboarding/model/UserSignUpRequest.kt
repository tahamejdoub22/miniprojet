package com.gmail.apigeoneer.miniprojets.onboarding.model

data class UserSignUpRequest(
    val email:String, val username:String, val password:String,
    val role: List<String>
)
