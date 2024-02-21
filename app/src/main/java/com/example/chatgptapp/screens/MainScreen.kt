package com.example.chatgptapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chatgptapp.components.EditTextField
import com.example.chatgptapp.model.request.RequestBody
import com.example.chatgptapp.viewmodel.MainScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    var text by remember {
        mutableStateOf("")
    }

    var promptText by remember {
        mutableStateOf("Question")
    }

    var answerText by remember {
        mutableStateOf("Answer")
    }


    LaunchedEffect(Unit) {
        viewModel.result.collectLatest {
            if (it.isNotEmpty())
                answerText = it[0].text
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = promptText,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f),
                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(fontWeight = FontWeight.Medium)
            )
            Text(
                text = answerText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .fillMaxHeight(0.9f),
                color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(fontWeight = FontWeight.Medium)
            )
        }
        EditTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
            text = text,
            label = "Enter your question",
            onTextChange = {
                text = it
            },
            onImeAction = {
                promptText = text
                text = ""
                viewModel.getPrompt(RequestBody(prompt = promptText))
            })
    }
}

