package com.example.terrestrial.data.response

import com.google.gson.annotations.SerializedName

class PredictResponse (

    @field:SerializedName("loginResult")
    val prediction: String? = null,
)