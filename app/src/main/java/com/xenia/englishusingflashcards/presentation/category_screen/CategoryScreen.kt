package com.xenia.englishusingflashcards.presentation.category_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.presentation.Header

@Composable
fun CategoryScreen(navController : NavController) {
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                navController = navController
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Header(contentPadding, "Категории", "to_main_screen", navController, activity)
            CategoryList(navController)
        }
    }
}

@Preview
@Composable
fun PreviewCategoryScreen() {
    CategoryScreen(navController = rememberNavController())
}