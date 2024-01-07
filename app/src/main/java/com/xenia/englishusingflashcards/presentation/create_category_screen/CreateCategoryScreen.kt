package com.xenia.englishusingflashcards.presentation.create_category_screen

import android.app.Application
import android.util.Log
import android.widget.EditText
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.navigation.NavigationItem
import com.xenia.englishusingflashcards.presentation.category_screen.SetProgressBar
import com.xenia.englishusingflashcards.room.entities.Word
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModel
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModelFactory


@Composable
fun CreateCategoryScreen(navController : NavController) {

    val wordsInCreatedCategory = listOf<Word>()

    Scaffold(
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(NavigationItem.Main.route) {
                            popUpTo(NavigationItem.Category.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 7.dp),
                    text = "Создать категорию",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth().weight(0.5f)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 10.dp
                    )
                    .background(color = Color.White)
                    .clip(RoundedCornerShape(25.dp))
                    .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.test_compressed), contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(25.dp))
                        )
                        StyledTextField()
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth().weight(1f)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 10.dp
                    )
                    .background(color = Color.White)
                    .clip(RoundedCornerShape(25.dp))
                    .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Слова в категории",
                            modifier = Modifier.padding(top = 10.dp).weight(0.15f))
                        Log.d("CategoryScreen", wordsInCreatedCategory.toString())
                        if (wordsInCreatedCategory.isNotEmpty()) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth().weight(0.8f)
                            ) {
                                items(wordsInCreatedCategory) { (id, categoryName, word, translate, sentence) ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp, horizontal = 20.dp)
                                            .clip(RoundedCornerShape(25.dp))
                                            .background(Color.LightGray),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = word,
                                        )
                                        Text(
                                            text = translate,
                                        )
                                        Text(text = sentence)
                                        Spacer(modifier = Modifier.height(10.dp))
                                    }
                                }
                            }
                        } else {
                            Text(text = "Нет слов в категории. Добавьте.",
                                modifier = Modifier.weight(0.7f))
                        }
                        Button(
                            onClick = {
//                                navController.navigate(NavigationItem.Category.route) {
//                                    popUpTo(NavigationItem.CreateCategory.route) {
//                                        inclusive = true
//                                    }
//                                }
                            },
                            Modifier
                                .fillMaxWidth().weight(0.15f)
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
                    }
                }
                Button(
                    onClick = {
                        navController.navigate(NavigationItem.Category.route) {
                            popUpTo(NavigationItem.CreateCategory.route) {
                                inclusive = true
                            }
                        }
                    },
                    Modifier
                        .fillMaxWidth().weight(0.15f)
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                    shape = RoundedCornerShape(25.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(202, 240, 248, 255),
                        contentColor = Color.Black)
                ){
                    Text(
                        "Создать",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCreateCategoryScreen() {
    CreateCategoryScreen(navController = rememberNavController())
}