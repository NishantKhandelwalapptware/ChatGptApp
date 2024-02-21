package com.example.chatgptapp.mapper

import com.example.chatgptapp.model.response.ChatGptData
import com.example.chatgptapp.model.response.ChatGptResponse
import com.example.chatgptapp.model.response.Choice
import com.example.chatgptapp.model.response.ChoiceData
import com.example.chatgptapp.model.response.Usage
import com.example.chatgptapp.model.response.UsageData
import com.example.chatgptapp.util.safeMap

fun ChatGptResponse.toChatGptData(): ChatGptData {
    return ChatGptData(
        choices = choices?.toChoiceData() ?: emptyList(),
        created = created ?: 0,
        id = id.orEmpty(),
        `object` = `object`.orEmpty(),
        usage = usage.toUsageData(),
        model = model.orEmpty()

    )
}

fun List<Choice>.toChoiceData(): List<ChoiceData> {
    return safeMap {
        ChoiceData(
            finish_reason = it.finish_reason.orEmpty(),
            index = it.index ?: 0,
            text = it.text.orEmpty()
        )
    }
}

fun Usage?.toUsageData(): UsageData {
    return UsageData(
        completion_tokens = this?.completion_tokens ?: 0,
        prompt_tokens = this?.prompt_tokens ?: 0,
        total_tokens = this?.total_tokens ?: 0
    )
}