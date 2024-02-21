package com.example.chatgptapp.model.response


data class ChatGptData(
    val choices: List<ChoiceData>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: UsageData
)

data class ChoiceData(
    val finish_reason: String,
    val index: Int,
    val text: String
)

data class UsageData(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
)