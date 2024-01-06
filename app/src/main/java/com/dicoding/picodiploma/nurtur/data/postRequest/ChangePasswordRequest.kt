package com.dicoding.picodiploma.nurtur.data.postRequest

data class ChangePasswordRequest(
    val password: String,
    val password_confirmation: String
)
