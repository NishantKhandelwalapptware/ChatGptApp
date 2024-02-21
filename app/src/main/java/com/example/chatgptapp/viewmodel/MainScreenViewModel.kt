package com.example.chatgptapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgptapp.model.request.RequestBody
import com.example.chatgptapp.model.response.ChoiceData
import com.example.chatgptapp.repository.ChatGptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ChatGptRepository
) : ViewModel() {

    private val _result = MutableStateFlow<List<ChoiceData>>(emptyList())
    val result = _result.asStateFlow()

    fun getPrompt(requestBody: RequestBody) {
        viewModelScope.launch {
            val response = repository.getPrompt(requestBody)
            if (response.data != null) {
                _result.value = response.data!!.choices
            }
        }
    }
}