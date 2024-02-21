package com.example.chatgptapp.repository

import android.util.Log
import com.example.chatgptapp.data.DataOrException
import com.example.chatgptapp.mapper.toChatGptData
import com.example.chatgptapp.model.request.RequestBody
import com.example.chatgptapp.model.response.ChatGptData
import com.example.chatgptapp.network.ApiService
import javax.inject.Inject

class ChatGptRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPrompt(body: RequestBody): DataOrException<ChatGptData, Boolean, Exception> {
        val response = try {
            apiService.getPrompt(body = body)
        } catch (exception: Exception) {
            return DataOrException(exception = exception)
        }
        Log.d("Repository data", "$response")
        return DataOrException(data = response.toChatGptData())
    }
}