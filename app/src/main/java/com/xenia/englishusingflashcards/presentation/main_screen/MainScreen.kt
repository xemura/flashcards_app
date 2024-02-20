package com.xenia.englishusingflashcards.presentation.main_screen

import android.app.Activity
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.navigation.NavigationItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.presentation.viewmodels.MainViewModel
import com.xenia.englishusingflashcards.presentation.viewmodels.MainViewModelFactory

@Composable
fun MainScreen(navController : NavController) {
    val activity = (LocalContext.current as? Activity)

    val mainViewModel: MainViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "MainViewModel",
        MainViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    // val boxSize = with(LocalDensity.current) { 200.dp.toPx() }
    Scaffold(
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
//            .background(
//                brush = Brush.linearGradient(
//                    colors = backgroundColors,
//                    start = Offset(0f, 0f), // top left corner
//                    end = Offset(boxSize, boxSize) // bottom right corner
//                )
//            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Header(contentPadding, "Главная", "close_app", navController, activity)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Привет!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                CardInMainScreen(mainViewModel.getCountWordsToStudy(), "учить")
                CardInMainScreen(mainViewModel.getCountWordsToKnow(), "знаю")
                CardInMainScreen(mainViewModel.getCountWordsToLearned(), "выучено")
            }

            Spacer(modifier = Modifier.height(136.dp))

            AlertDialogPlayground()
            ButtonMainScreen(navController, NavigationItem.CategoryMain.route, "Категории")
            ButtonMainScreen(navController, NavigationItem.LearningCard.route, "Обучение")

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.padding(bottom = 25.dp),
                text = "created by xemura",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 15.sp
            )
        }
    }
}