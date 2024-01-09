package com.xenia.englishusingflashcards.presentation.create_category_screen

import android.app.Application
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.englishusingflashcards.room.entities.Word
import com.xenia.englishusingflashcards.ui.theme.default
import com.xenia.englishusingflashcards.viewmodels.CreateCategoryViewModel
import com.xenia.englishusingflashcards.viewmodels.CreateCategoryViewModelFactory


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
fun AlertDialogAddWordInCategory(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    var word by remember { mutableStateOf(TextFieldValue()) }
    var errorStateWord by remember { mutableStateOf(false)}
    var errorMessageWord by remember { mutableStateOf("")}

    var translate by remember { mutableStateOf(TextFieldValue()) }
    var errorStateTranslate by remember { mutableStateOf(false)}
    var errorMessageTranslate by remember { mutableStateOf("")}

    var sentence by remember { mutableStateOf(TextFieldValue()) }
    var errorStateSentence by remember { mutableStateOf(false)}
    var errorMessageSentence by remember { mutableStateOf("")}

    val createCategoryViewModel: CreateCategoryViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "CreateCategoryViewModel",
        CreateCategoryViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (!(word.text.isEmpty() or translate.text.isEmpty() or sentence.text.isEmpty())) {
                        val createdWord = Word(
                            categoryName = createCategoryViewModel.categoryName,
                            word = word.text,
                            translate = translate.text,
                            sentence = sentence.text,
                            inProcess = false,
                            theDateOfTheWordStudy = "", // вычислять
                            theNumberOfRepetitions = 0,
                            theRepetitionInterval = 0.0,
                            theRepetitionIntervalAfterTheNRepetition = 0.0
                        )
                        Log.d("CreateCategory", createdWord.toString())
                        createCategoryViewModel.updateListWordsInCategory(createdWord)
                        onConfirm.invoke()
                    } else {
                        if (word.text.isEmpty()) {
                            word = TextFieldValue("")
                            errorStateWord = true
                            errorMessageWord = "Заполните поле"
                            Log.d("CreateCategory", "word value $errorStateWord")
                        }
                        if (translate.text.isEmpty()) {
                            translate = TextFieldValue("")
                            errorStateTranslate = true
                            errorMessageTranslate = "Заполните поле"
                            Log.d("CreateCategory", "translate value ${translate.text}")
                        }
                        if (sentence.text.isEmpty()) {
                            sentence = TextFieldValue("")
                            errorStateSentence = true
                            errorMessageSentence = "Заполните поле"
                            Log.d("CreateCategory", "sentence value ${sentence.text}")
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
            val checkedState = remember { mutableStateOf(false) }
            val textColor = remember { mutableStateOf(Color.Unspecified) }
            Column(modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
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
                        word = value
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
                    modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
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
                        translate = value
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
                    modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
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
                        sentence = value
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
                    modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
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
fun AlertDialogAddWordInCategoryPlayground() {
    val showAlertDialog = remember { mutableStateOf(false) }

    val createCategoryViewModel: CreateCategoryViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "CreateCategoryViewModel",
        CreateCategoryViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    Button(
        onClick = {
            if (createCategoryViewModel.categoryName.isNotEmpty()
                and (createCategoryViewModel.categoryName != ""))
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
        AlertDialogAddWordInCategory(
            onDismiss = {
                showAlertDialog.value = false },
            onConfirm = {
                showAlertDialog.value = false },
        )
    }
}