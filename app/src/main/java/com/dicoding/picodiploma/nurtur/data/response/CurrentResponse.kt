package com.dicoding.picodiploma.nurtur.data.response

import com.google.gson.annotations.SerializedName

data class CurrentResponse(

	@field:SerializedName("message")
	val message: Message
)

data class Message(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("token")
	val token: String
)
