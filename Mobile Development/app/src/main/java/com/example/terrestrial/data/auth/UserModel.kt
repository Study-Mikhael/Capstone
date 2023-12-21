package com.example.terrestrial.data.auth

data class UserModel(
    val email: String = "",
    val name: String = "",
    val token: String = "",
    val isLogin: Boolean = false,
    val isAnswer: Boolean = false,
    val resultRecommendation: String = ""
)
