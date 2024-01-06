package com.dicoding.picodiploma.nurtur.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("data")
    val data: DataRegister?,

    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("error")
    val error: String?
)

data class DataRegister(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("email")
    val email: String
)
