package com.example.forgetpass.model

data class ForgetPasswordModel(
    val email: String = "",
    val code: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)
