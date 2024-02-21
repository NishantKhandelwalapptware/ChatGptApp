package com.example.chatgptapp.model.request

import com.google.gson.annotations.SerializedName

data class RequestBody(
    @SerializedName("model")
    val model: String = "gpt-3.5-turbo-instruct",
    @SerializedName("prompt")
    val prompt: String
)
