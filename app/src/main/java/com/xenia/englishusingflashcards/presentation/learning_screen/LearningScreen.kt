package com.xenia.englishusingflashcards.presentation.learning_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.navigation.NavigationItem
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.presentation.category_screen.category_list_screen.CategoryList
import com.xenia.englishusingflashcards.presentation.category_screen.category_list_screen.ExtendedFloatingActionButton
import com.xenia.englishusingflashcards.presentation.learning_screen.component_swipe_card.SwipeableCard

@Composable
fun LearningScreen(navController : NavController) {
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                navController = navController
//            )
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
            Header(
                contentPadding,
                "Тренировка",
                "to_main_screen_from_learn",
                navController,
                activity
            )
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SwipeableCard(
                        dataSource = (0..10).map { 0 }.toList()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLearningScreen() {
    LearningScreen(navController = rememberNavController())
}