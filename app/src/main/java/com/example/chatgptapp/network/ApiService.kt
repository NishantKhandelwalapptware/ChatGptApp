package com.example.chatgptapp.network

import com.example.chatgptapp.model.request.RequestBody
import com.example.chatgptapp.model.response.ChatGptResponse
import com.example.chatgptapp.util.Constants
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ApiService {
    @POST("v1/completions")
    suspend fun getPrompt(
        @Header("Authorization") authorization: String = Constants.API_KEY,
        @Body body: RequestBody
    ): ChatGptResponse
}