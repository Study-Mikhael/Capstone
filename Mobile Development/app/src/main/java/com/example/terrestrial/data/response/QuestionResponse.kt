package com.example.terrestrial.data.response

import com.google.gson.annotations.SerializedName

data class QuestionResponse(

	@field:SerializedName("data")
	val data: List<QuestionItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class QuestionItem(

	@field:SerializedName("queText")
	val queText: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("answer1")
	val answer1: String? = null,

	@field:SerializedName("answer2")
	val answer2: String? = null,

	@field:SerializedName("answer3")
	val answer3: String? = null
)
