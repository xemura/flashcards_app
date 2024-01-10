package com.xenia.englishusingflashcards.presentation.create_category_screen

import android.app.Activity
import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.navigation.NavigationItem
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.viewmodels.CreateCategoryViewModel
import com.xenia.englishusingflashcards.viewmodels.CreateCategoryViewModelFactory


@Composable
fun CreateCategoryScreen(navController : NavController) {
    val activity = (LocalContext.current as? Activity)

    val createCategoryViewModel: CreateCategoryViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "CreateCategoryViewModel",
        CreateCategoryViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )


    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Header(contentPadding, "Создать категорию", "to_category_screen", navController, activity)

            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
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
                    CardEnterCategoryName(createCategoryViewModel)
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
                    ContentCardAddWordInCreateCategory(createCategoryViewModel)
                }
                Button(
                    onClick = {
                        if (createCategoryViewModel.categoryName.isNotEmpty()) {
                            createCategoryViewModel.saveCategoryWithWords()
                            navController.navigate(NavigationItem.Category.route) {
                                popUpTo(NavigationItem.CreateCategory.route) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    Modifier.fillMaxWidth().weight(0.16f)
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    shape = RoundedCornerShape(25.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(202, 240, 248, 255),
                        contentColor = Color.Black
                    )
                ) {
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