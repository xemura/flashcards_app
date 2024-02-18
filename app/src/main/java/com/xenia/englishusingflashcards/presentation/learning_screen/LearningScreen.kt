package com.xenia.englishusingflashcards.presentation.learning_screen

import android.app.Activity
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.presentation.viewmodels.LearningViewModel
import com.xenia.englishusingflashcards.presentation.viewmodels.LearningViewModelFactory

@Composable
fun LearningScreen(navController: NavController) {
    val activity = (LocalContext.current as? Activity)

    val learningViewModel: LearningViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "LearningViewModel",
        LearningViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    val listLearnWords = learningViewModel.wordsToStudy.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton()
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
                contentPadding, "Тренировка",
                "to_main_screen_from_learn",
                navController,
                activity
            )
            ContentScreen(listLearnWords = listLearnWords, learningViewModel)
        }
    }
}