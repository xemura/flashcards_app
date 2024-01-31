package com.xenia.englishusingflashcards.presentation.learning_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.CardFace
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.FlipCard

@Composable
fun LearningScreen(navController : NavController) {
    val activity = (LocalContext.current as? Activity)

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
                contentPadding,
                "Тренировка",
                "to_main_screen_from_learn",
                navController,
                activity
            )
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    var cardFace by remember {
                        mutableStateOf(CardFace.Front)
                    }

                    FlipCard(
                        cardFace = cardFace,
                        onClick = { cardFace = cardFace.next },
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .aspectRatio(1f),
                        front = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "Word",
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            }
                        },
                        back = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                contentAlignment = Alignment.Center,
                            ) {
                                Column {
                                    Text(
                                        text = "Translate",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                    Text(
                                        text = "Sentence",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}