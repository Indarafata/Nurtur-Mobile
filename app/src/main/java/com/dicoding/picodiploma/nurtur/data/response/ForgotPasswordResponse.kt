package com.dicoding.picodiploma.nurtur.data.response

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(

	@field:SerializedName("data")
	val data: DataForgotPassword,

	@field:SerializedName("message")
	val message: String
)

data class DataForgotPassword(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("token")
	val token: String
)
