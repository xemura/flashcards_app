package com.xenia.englishusingflashcards.presentation.learning_screen

import android.app.Activity
import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.navigation.NavigationItem
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.CardFace
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.FlipCard
import com.xenia.englishusingflashcards.presentation.viewmodels.CategoryViewModel
import com.xenia.englishusingflashcards.presentation.viewmodels.CategoryViewModelFactory
import com.xenia.englishusingflashcards.presentation.viewmodels.LearningViewModel
import com.xenia.englishusingflashcards.presentation.viewmodels.LearningViewModelFactory

@Composable
fun LearningScreen(navController : NavController) {
    val activity = (LocalContext.current as? Activity)

    val learningViewModel: LearningViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "LearningViewModel",
        LearningViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    val data = learningViewModel.getWordToStudy()

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

                var cardFace by remember {
                    mutableStateOf(CardFace.Front)
                }

                var indexList by remember {
                    mutableIntStateOf(0)
                }

                if (indexList == data.size) {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "Больше карточек для изучения нет"
                    )
                } else {
                    Box(
                        modifier = Modifier.wrapContentHeight()
                            .padding(top = 100.dp)
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        FlipCard(
                            cardFace = cardFace,
                            onClick = { cardFace = cardFace.next },
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            front = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        text = data[indexList].word,
                                        textAlign = TextAlign.Center,
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
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 20.dp),
                                            text = data[indexList].translate,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyLarge,
                                        )
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 20.dp),
                                            text = data[indexList].sentence,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyLarge,
                                        )
                                    }
                                }
                            },
                        )
                    }

                    Button(
                        onClick = {
                            if (CardFace.Back == cardFace) {
                                if (indexList != data.size) {
                                    indexList++
                                    cardFace = cardFace.next
                                }
                            }
                        },
                        Modifier
                            .fillMaxWidth()
                            //.wrapContentHeight()
                            .padding(20.dp),
                        shape = RoundedCornerShape(25.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(202, 240, 248, 255),
                            contentColor = Color.Black)
                    ){
                        Text(
                            text = "NEXT",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}