package com.xenia.englishusingflashcards.presentation.learning_screen

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.CardFace
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.FlipCard
import com.xenia.englishusingflashcards.presentation.viewmodels.LearningViewModel
import com.xenia.englishusingflashcards.presentation.viewmodels.LearningViewModelFactory
import kotlin.math.abs
import kotlin.math.roundToInt


sealed class SwipeDirection {
    data object Left : SwipeDirection()
    data object Right : SwipeDirection()
}

@OptIn(ExperimentalFoundationApi::class)
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

    learningViewModel.getWordToStudy()

    val listLearnWords = learningViewModel.listLearnWords.observeAsState()

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
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {

                var cardFace by remember {
                    mutableStateOf(CardFace.Front)
                }

                var indexList by remember {
                    mutableIntStateOf(0)
                }

                var offsetX by remember { mutableStateOf(0f) }

                var offsetY by remember { mutableStateOf(0f) }

                if (listLearnWords.value != null) {
                    if (indexList == listLearnWords.value!!.wordsListToStudy.size) {
                        Text(
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center,
                            text = "Больше карточек для изучения нет"
                        )
                    } else {
                        // свайп влево вправо и наверх
                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .padding(top = 100.dp)
                                .padding(horizontal = 20.dp)
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            val (x, y) = dragAmount
                                            offsetX += dragAmount.x

                                            when {
                                                (x > 100 && y > 0) -> {
                                                    if (CardFace.Back == cardFace) {
                                                        if (indexList != listLearnWords.value!!.wordsListToStudy.size) {
                                                            indexList++
                                                            cardFace = cardFace.next
                                                        }
                                                    }
                                                }
                                                (x < -100 && y > 0) -> {
                                                    if (CardFace.Back == cardFace) {
                                                        if (indexList != listLearnWords.value!!.wordsListToStudy.size) {
                                                            indexList++
                                                            cardFace = cardFace.next
                                                        }
                                                    }
                                                }
                                            }
                                        },
                                        onDragEnd = {
                                            offsetX = 0f
                                            offsetY = 0f
                                        }
                                    )
                                }.offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) },
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
                                            text = listLearnWords.value!!.wordsListToStudy[indexList].word,
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
                                                text = listLearnWords.value!!.wordsListToStudy[indexList].translate,
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.bodyLarge,
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 20.dp),
                                                text = listLearnWords.value!!.wordsListToStudy[indexList].sentence,
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

                            },
                            Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            shape = RoundedCornerShape(25.dp),
                            border = BorderStroke(1.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(202, 240, 248, 255),
                                contentColor = Color.Black
                            )
                        ) {
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
}

//suspend fun PointerInputScope.detectSwipe(
//    offsetX: Float,
//    swipeState: MutableIntState = mutableIntStateOf(-1),
//    onSwipeLeft: () -> Unit = {},
//    onSwipeRight: () -> Unit = {},
//    onSwipeUp: () -> Unit = {},
//    onSwipeDown: () -> Unit = {},
//) = detectDragGestures(
//    onDrag = { change, dragAmount ->
//        change.consume()
//        val (x, y) = dragAmount
//        offsetX += dragAmount.x
//        if (abs(x) > abs(y)) {
//            when {
//                x > 0 -> swipeState.intValue = 0
//                x < 0 -> swipeState.intValue = 1
//            }
//        } else {
//            when {
//                y > 0 -> swipeState.intValue = 2
//                y < 0 -> swipeState.intValue = 3
//            }
//        }
//    },
//    onDragEnd = {
//        when (swipeState.intValue) {
//            0 -> onSwipeRight()
//            1 -> onSwipeLeft()
//            2 -> onSwipeDown()
//            3 -> onSwipeUp()
//        }
//    }
//)