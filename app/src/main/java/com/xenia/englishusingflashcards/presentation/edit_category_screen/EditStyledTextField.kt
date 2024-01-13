package com.xenia.englishusingflashcards.presentation.edit_category_screen

import android.util.Log
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.englishusingflashcards.ui.theme.default
import com.xenia.englishusingflashcards.viewmodels.EditCategoryViewModel


private val textFieldColors = listOf(
    Color(0xFF184E77),
    Color(0xFF1E6091),
    Color(0xFF1A759F),
    Color(0xFF168AAD),
    Color(0xFF4DA2C7),
    Color(0xFF4DBFC7),
    Color(0xFF7DD8C7),
    Color(0xFFA2E3C8),
    Color(0xFFB8E7C8),
    Color(0xFFD3EBCD),
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditStyledTextField(editCategoryViewModel: EditCategoryViewModel) {

    val keyboardController = LocalSoftwareKeyboardController.current
    var errorStateCategoryName by remember { mutableStateOf(false) }
    var errorMessageCategoryName by remember { mutableStateOf("") }

    val brush = remember {
        Brush.linearGradient(
            colors = textFieldColors
        )
    }

    Log.d("Tag", "- ${editCategoryViewModel.categoryName}")

    OutlinedTextField(
        value = editCategoryViewModel.categoryName,
        onValueChange =
        { value ->
            Log.d("Tag", "EditStyledTextField ${editCategoryViewModel.categoryName}")
            if (value.length <= 12) editCategoryViewModel.updateCategoryName(value)
            when {
                editCategoryViewModel.categoryName.isEmpty() -> {
                    errorStateCategoryName = true
                    errorMessageCategoryName = "Заполните поле"
                }
                editCategoryViewModel.categoryName == "" -> {
                    errorStateCategoryName = true
                    errorMessageCategoryName = "Пустое поле"
                }
                else -> {
                    errorStateCategoryName = false
                    errorMessageCategoryName = ""
                }
            }
        },
        label =
        {
            Text(
                text = if (errorStateCategoryName) errorMessageCategoryName
                else "Введите название", style = MaterialTheme.typography.bodyLarge
            )
        },
        placeholder = { Text(text = "Название", style = MaterialTheme.typography.bodyLarge) },
        singleLine = true,
        textStyle = TextStyle(brush = brush, fontFamily = default, fontSize = 16.sp),
        modifier = Modifier.padding(bottom = 10.dp, top = 10.dp, start = 20.dp, end = 20.dp),
        isError = errorStateCategoryName,
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