package com.dicoding.picodiploma.nurtur.data.response

import com.google.gson.annotations.SerializedName

data class MachineLearningResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: Status
)

data class Data(

	@field:SerializedName("emotion")
	val emotion: String,

	@field:SerializedName("image_path")
	val imagePath: String
)

data class Status(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String
)
