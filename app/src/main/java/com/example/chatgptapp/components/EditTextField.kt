package com.example.chatgptapp.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTextField(
    modifier: Modifier,
    text: String,
    label: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = {
            Text(text = label)
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor =  Color.Transparent,
            unfocusedIndicatorColor =  Color.Transparent,
            disabledIndicatorColor =  Color.Transparent
        ),
        shape = RoundedCornerShape(32.dp),
    )
}