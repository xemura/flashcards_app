package com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.ui.theme.default
import com.xenia.englishusingflashcards.presentation.viewmodels.EditCategoryViewModel

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
fun AlertDialogAddWordInEditCategory(editCategoryViewModel: EditCategoryViewModel,
                                     onDismiss: () -> Unit, onConfirm: () -> Unit) {
    var word by remember { mutableStateOf(TextFieldValue()) }
    var errorStateWord by remember { mutableStateOf(false) }
    var errorMessageWord by remember { mutableStateOf("") }

    var translate by remember { mutableStateOf(TextFieldValue()) }
    var errorStateTranslate by remember { mutableStateOf(false) }
    var errorMessageTranslate by remember { mutableStateOf("") }

    var sentence by remember { mutableStateOf(TextFieldValue()) }
    var errorStateSentence by remember { mutableStateOf(false) }
    var errorMessageSentence by remember { mutableStateOf("") }

    val maxChar = 50

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (!(word.text.isEmpty() or translate.text.isEmpty() or sentence.text.isEmpty())) {
                        val createdWord = Word(
                            categoryName = editCategoryViewModel.categoryName.value!!, // !!!!!!!!!!!!11
                            word = word.text,
                            translate = translate.text,
                            sentence = sentence.text,
                            inProcess = false,
                            theDateOfTheWordStudy = "", // вычислять
                            theNumberOfRepetitions = 0,
                            theRepetitionInterval = 0.0,
                            theRepetitionIntervalAfterTheNRepetition = 0.0
                        )

                        editCategoryViewModel.updateListWordsInCategory(createdWord) /////////////////
                        onConfirm.invoke()
                    } else {
                        if (word.text.isEmpty()) {
                            word = TextFieldValue("")
                            errorStateWord = true
                            errorMessageWord = "Заполните поле"
                        }
                        if (translate.text.isEmpty()) {
                            translate = TextFieldValue("")
                            errorStateTranslate = true
                            errorMessageTranslate = "Заполните поле"
                        }
                        if (sentence.text.isEmpty()) {
                            sentence = TextFieldValue("")
                            errorStateSentence = true
                            errorMessageSentence = "Заполните поле"
                        }
                    }
                },
                modifier = Modifier.size(width = 90.dp, height = 60.dp),
                shape = RoundedCornerShape(25.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(202, 240, 248, 255),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "ОК",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss.invoke()
                },
                modifier = Modifier.size(width = 120.dp, height = 60.dp),
                shape = RoundedCornerShape(25.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(202, 240, 248, 255),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
        text = {
            Column(modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                val keyboardController = LocalSoftwareKeyboardController.current

                val brush = remember {
                    Brush.linearGradient(
                        colors = textFieldColors
                    )
                }

                OutlinedTextField(
                    value = word,
                    onValueChange =
                    { value ->
                        if (value.text.length <= maxChar) word = value
                        when {
                            word.text.isEmpty() -> {
                                errorStateWord = true
                                errorMessageWord = "Заполните поле"
                            }
                            word.text == "" -> {
                                errorStateWord = true
                                errorMessageWord = "Пустое поле"
                            }
                            else -> {
                                errorStateWord = false
                                errorMessageWord = ""
                            }
                        }
                    },
                    supportingText = {
                        if (word.text.length >= maxChar) {
                            Text(
                                text = "${word.text.length} / $maxChar",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                            )
                        }

                    },
                    label =
                    {
                        Text(
                            text = if (errorStateWord) errorMessageWord
                            else "Введите слово", style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder =
                    {
                        Text(
                            text = if (errorStateWord) errorMessageWord
                            else "Слово", style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    maxLines = 2,
                    textStyle = TextStyle(brush = brush, fontFamily = default, fontSize = 16.sp),
                    isError = errorStateWord,
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

                OutlinedTextField(
                    value = translate,
                    onValueChange =
                    { value ->
                        if (value.text.length <= maxChar) translate = value
                        when {
                            translate.text.isEmpty() -> {
                                errorStateTranslate = true
                                errorMessageTranslate = "Заполните поле"
                            }
                            translate.text == "" -> {
                                errorStateTranslate = true
                                errorMessageTranslate = "Пустое поле"
                            }
                            else -> {
                                errorStateTranslate = false
                                errorMessageTranslate = ""
                            }
                        }
                    },
                    supportingText = {
                        if (translate.text.length >= maxChar) {
                            Text(
                                text = "${translate.text.length} / $maxChar",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                            )
                        }

                    },
                    label =
                    {
                        Text(
                            text = if (errorStateTranslate) errorMessageTranslate
                            else "Введите перевод", style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder =
                    {
                        Text(
                            text = if (errorStateTranslate) errorMessageTranslate
                            else "Перевод", style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    maxLines = 2,
                    textStyle = TextStyle(brush = brush, fontFamily = default, fontSize = 16.sp),
                    isError = errorStateTranslate,
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

                OutlinedTextField(
                    value = sentence,
                    { value ->
                        if (value.text.length <= maxChar) sentence = value
                        when {
                            sentence.text.isEmpty() -> {
                                errorStateSentence = true
                                errorMessageSentence = "Заполните поле"
                            }
                            sentence.text == "" -> {
                                errorStateSentence = true
                                errorMessageSentence = "Пустое поле"
                            }
                            else -> {
                                errorStateSentence = false
                                errorMessageSentence = ""
                            }
                        }
                    },
                    supportingText = {
                        if (sentence.text.length >= maxChar) {
                            Text(
                                text = "${sentence.text.length} / $maxChar",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                            )
                        }

                    },
                    label =
                    {
                        Text(
                            text = if (errorStateSentence) errorMessageSentence
                            else "Введите предложение", style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder =
                    {
                        Text(
                            text = if (errorStateSentence) errorMessageSentence
                            else "Перевод", style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    maxLines = 2,
                    textStyle = TextStyle(brush = brush, fontFamily = default, fontSize = 16.sp),
                    isError = errorStateSentence,
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
        }
    )
}

@Composable
fun AlertDialogAddWordInEditCategoryPlayground(editCategoryViewModel: EditCategoryViewModel) {
    val showAlertDialog = remember { mutableStateOf(false) }

    Button(
        onClick = {
            if ((editCategoryViewModel.categoryName.value?.isNotEmpty() == true)
                and (editCategoryViewModel.categoryName.value != ""))
            {
                showAlertDialog.value = true
            }
        },
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(202, 240, 248, 255),
            contentColor = Color.Black
        )
    ){
        Text(
            "Добавить",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }

    if (showAlertDialog.value) {
        AlertDialogAddWordInEditCategory(
            editCategoryViewModel,
            onDismiss = {
                showAlertDialog.value = false },
            onConfirm = {
                showAlertDialog.value = false },
        )
    }
}