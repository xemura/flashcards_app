package com.xenia.englishusingflashcards.presentation.learning_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.BackFaceCard
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.CardFace
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.FlipCard
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.FrontFaceCard
import com.xenia.englishusingflashcards.presentation.viewmodels.LearningViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun ContentScreen(listLearnWords: State<List<WordsStudyModel>?>, learningViewModel: LearningViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {

        var cardFace by remember {
            mutableStateOf(CardFace.Front)
        }

        var currentCard by remember {
            mutableStateOf(WordsStudyModel())
        }

        val data = listLearnWords.value

        val states = data?.reversed()
            ?.map { it to rememberSwipeableCardState() }


        val mLocalContext = LocalContext.current

        val scope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            if (data != null) {
                states?.forEach { (matchProfile, state) ->
                    if (state.swipedDirection == null) {
                        Column {
                            FlipCard(
                                cardFace = cardFace,
                                onClick = { cardFace = cardFace.next },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .swipableCard(
                                        state = state,
                                        blockedDirections = listOf(Direction.Up),
                                        onSwiped = { direction ->
                                            when (direction) {
                                                Direction.Left -> {
                                                    if (CardFace.Back == cardFace) {
                                                        cardFace = cardFace.next
                                                    }
                                                    Toast
                                                        .makeText(
                                                            mLocalContext,
                                                            "Не Понял",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                        .show()
                                                }

                                                Direction.Right -> {
                                                    if (CardFace.Back == cardFace) {
                                                        cardFace = cardFace.next
                                                    }
                                                    Toast
                                                        .makeText(
                                                            mLocalContext,
                                                            "Понял",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                        .show()
                                                }

                                                Direction.Down -> {
                                                    if (CardFace.Back == cardFace) {
                                                        cardFace = cardFace.next
                                                    }
                                                    Toast
                                                        .makeText(
                                                            mLocalContext,
                                                            "Delete",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                        .show()
                                                }

                                                else -> {}
                                            }
                                            println("The card was swiped to $direction")
                                        },
                                        onSwipeCancel = {
                                            println("The card was cancelled")
                                        }
                                    ),
                                front = {
                                    currentCard = matchProfile
                                    FrontFaceCard(matchProfile)
                                },
                                back = {
                                    currentCard = matchProfile
                                    BackFaceCard(matchProfile)
                                },
                            )

                            Row(
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                IconButton(modifier = Modifier.size(70.dp),
                                    onClick = {
                                        if (CardFace.Back == cardFace) {
                                            scope.launch {
                                                cardFace = cardFace.next
                                                delay(500L)
                                                state.swipe(Direction.Left)
                                                Toast.makeText(
                                                    mLocalContext,
                                                    "Не понял",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }

                                        } else {
                                            error(mLocalContext)
                                        }
                                    })
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_repeat),
                                        contentDescription = "repeat card"
                                    )
                                }

                                IconButton(modifier = Modifier.size(70.dp),
                                    onClick = {
                                        if (CardFace.Back == cardFace) {
                                            scope.launch {
                                                cardFace = cardFace.next
                                                delay(500L)
                                                state.swipe(Direction.Down)
                                                learningViewModel.deleteWordFromTableStudy(
                                                    currentCard.word,
                                                    currentCard.translate,
                                                    currentCard.sentence
                                                )
                                            }

                                        } else {
                                            error(mLocalContext)
                                        }
                                    }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_delete),
                                        contentDescription = "delete card"
                                    )
                                }

                                IconButton(modifier = Modifier.size(70.dp),
                                    onClick = {
                                        if (CardFace.Back == cardFace) {
                                            scope.launch {
                                                cardFace = cardFace.next
                                                delay(500L)
                                                state.swipe(Direction.Right)
                                                Toast
                                                    .makeText(
                                                        mLocalContext,
                                                        "Понял",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            }
                                            scope.launch {
                                                Log.d("Know", currentCard.id.toString())
                                                learningViewModel.guessedCardAndMoveToKnowState(
                                                    currentCard.id
                                                )
                                            }
                                        } else {
                                            error(mLocalContext)
                                        }
                                    }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_done),
                                        contentDescription = "memorize card"
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                println("fffffffff")
                Text(
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    text = "Больше карточек для изучения нет"
                )
            }
        }
    }
}

private fun error(mLocalContext: Context) {
    Toast
        .makeText(
            mLocalContext,
            "Переверните карточку",
            Toast.LENGTH_SHORT
        )
        .show()
}

