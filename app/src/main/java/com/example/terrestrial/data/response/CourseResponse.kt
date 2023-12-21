package com.example.terrestrial.data.response

import com.google.gson.annotations.SerializedName

data class CourseResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("courseName")
	val courseName: String? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("courseType")
	val courseType: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

