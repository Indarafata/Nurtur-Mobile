package com.dicoding.picodiploma.nurtur.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: DataLogin,

	@field:SerializedName("message")
	val message: String
)

data class DataLogin(

	@field:SerializedName("token")
	val token: String
)
