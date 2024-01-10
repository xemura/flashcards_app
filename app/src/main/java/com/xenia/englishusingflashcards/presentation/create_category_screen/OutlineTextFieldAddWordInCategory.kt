package com.xenia.englishusingflashcards.presentation.create_category_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.xenia.englishusingflashcards.ui.theme.default

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutlineTextFieldAddWordInCategory(
    labelText: String,
    placeholderText: String,
    maxChar: Int,
    keyboardController: SoftwareKeyboardController?,
    brush: Brush?
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    var errorState by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange =
        { value ->
            if (value.text.length <= maxChar) text = value
            when {
                text.text.isEmpty() -> {
                    errorState = true
                    errorMessage = "Заполните поле"
                }
                text.text == "" -> {
                    errorState = true
                    errorMessage = "Пустое поле"
                }
                else -> {
                    errorState = false
                    errorMessage = ""
                }
            }
        },
        supportingText = {
            if (text.text.length >= maxChar) {
                Text(
                    text = "${text.text.length} / $maxChar",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            }

        },
        label =
        {
            Text(
                text = if (errorState) errorMessage
                else labelText, style = MaterialTheme.typography.bodyLarge
            )
        },
        placeholder =
        {
            Text(
                text = if (errorState) errorMessage
                else placeholderText, style = MaterialTheme.typography.bodyLarge
            )
        },
        maxLines = 2,
        textStyle = TextStyle(brush = brush, fontFamily = default, fontSize = 16.sp),
        isError = errorState,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Black,
            focusedContainerColor = Color.White,
            focusedLabelColor = Color.Black,
            unfocusedContainerColor = Color.White,
            errorIndicatorColor = Color(0xFFE30022),
            errorContainerColor = Color.White,
            errorLabelColor = Color(0xFFFF0800),
            errorCursorColor = Color(0xFF960018)
        )
    )

}