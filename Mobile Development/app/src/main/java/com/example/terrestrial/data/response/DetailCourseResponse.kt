package com.example.terrestrial.data.response

import com.google.gson.annotations.SerializedName

data class DetailCourseResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("courseName")
	val courseName: String? = null,

	@field:SerializedName("learning")
	val learning: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("describe")
	val describe: String? = null
)

