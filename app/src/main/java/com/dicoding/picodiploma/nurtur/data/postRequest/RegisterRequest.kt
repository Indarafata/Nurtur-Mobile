package com.dicoding.picodiploma.nurtur.data.postRequest

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String
)