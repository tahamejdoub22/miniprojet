package com.example.myapplication.ui.main.data.model

data class UserSignUpRequest(
    val email:String, val username:String, val password:String,
    val role: List<String>
)
