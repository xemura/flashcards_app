package com.xenia.englishusingflashcards.presentation.create_category_screen

import android.app.Application
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.englishusingflashcards.room.entities.Word
import com.xenia.englishusingflashcards.ui.theme.default
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModel
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModelFactory
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
    var word by remember { mutableStateOf("") }
    var translate by remember { mutableStateOf("") }
    var sentence by remember { mutableStateOf("") }

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
        dismissButton = {
            Button(
                onClick = {
                    onDismiss.invoke()
                },
                modifier = Modifier.fillMaxWidth().size(50.dp),
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
        confirmButton = {
            Button(
                onClick = {
                    val createdWord = Word(
                        categoryName = createCategoryViewModel.categoryName,
                        word = word,
                        translate = translate,
                        sentence = sentence,
                        inProcess = false,
                        theDateOfTheWordStudy = "", // вычислять
                        theNumberOfRepetitions = 0,
                        theRepetitionInterval = 0.0,
                        theRepetitionIntervalAfterTheNRepetition = 0.0
                    )
                    Log.d("CreateCategory", createdWord.toString())
                    createCategoryViewModel.updateListWordsInCategory(createdWord)
                    onConfirm.invoke()
                },
                modifier = Modifier.fillMaxWidth().size(50.dp),
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
        containerColor = Color.White,
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier.clip(RoundedCornerShape(25.dp))
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
                    onValueChange = { value -> word = value },
                    label = { Text("Введите слово", style = MaterialTheme.typography.bodyLarge) },
                    placeholder = { Text(text = "Слово", style = MaterialTheme.typography.bodyLarge) },
                    maxLines = 2,
                    textStyle = TextStyle(brush = brush, fontFamily = default, fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
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
                        unfocusedContainerColor = Color.White
                    )
                )

                OutlinedTextField(
                    value = translate,
                    onValueChange = { value -> translate = value },
                    label = { Text("Введите перевод", style = MaterialTheme.typography.bodyLarge) },
                    placeholder = { Text(text = "Перевод", style = MaterialTheme.typography.bodyLarge) },
                    maxLines = 2,
                    textStyle = TextStyle(brush = brush, fontFamily = default, fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
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
                        unfocusedContainerColor = Color.White
                    )
                )

                OutlinedTextField(
                    value = sentence,
                    onValueChange = { value -> sentence = value },
                    label = { Text("Введите предложение", style = MaterialTheme.typography.bodyLarge) },
                    placeholder = { Text(text = "Предложение", style = MaterialTheme.typography.bodyLarge) },
                    maxLines = 2,
                    textStyle = TextStyle(brush = brush, fontFamily = default, fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
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
                        unfocusedContainerColor = Color.White
                    )
                )
            }
        }
    )
}

@Composable
fun AlertDialogAddWordInCategoryPlayground() {
    val showAlertDialog = remember { mutableStateOf(false) }

    Button(
        onClick = {
            showAlertDialog.value = true
        },
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(202, 240, 248, 255),
            contentColor = Color.Black)
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