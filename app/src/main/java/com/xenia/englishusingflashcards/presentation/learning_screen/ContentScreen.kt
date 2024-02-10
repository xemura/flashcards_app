package com.xenia.englishusingflashcards.presentation.learning_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.BackFaceCard
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.CardFace
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.FlipCard
import com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card.FrontFaceCard
import com.xenia.englishusingflashcards.ui.theme.LightGreen
import com.xenia.englishusingflashcards.ui.theme.LightRed
import com.xenia.englishusingflashcards.ui.theme.LightYellow
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ContentScreen(listLearnWords: State<WordsStudyModel?>) {
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

        if (listLearnWords.value != null) {
            if (indexList == listLearnWords.value!!.wordsListToStudy.size) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    text = "Больше карточек для изучения нет"
                )
            } else {

                val mLocalContext = LocalContext.current

                val actionLearnedWord = SwipeAction(
                    icon = painterResource(id = R.drawable.ic_done),
                    background = LightGreen,
                    isUndo = true,
                    onSwipe = {
                        if (CardFace.Back == cardFace) {
                            indexList++
                            cardFace = cardFace.next
                        }
                        Toast.makeText(
                            mLocalContext,
                            "Понял",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )

                val actionDeleteWord = SwipeAction(
                    icon = painterResource(id = R.drawable.ic_delete),
                    background = LightRed,
                    isUndo = true,
                    onSwipe = {
                        if (CardFace.Back == cardFace) {
                            indexList++
                            cardFace = cardFace.next
                        }
                        Toast.makeText(
                            mLocalContext,
                            "Удалить",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )

                val actionRepeatWord = SwipeAction(
                    icon = painterResource(R.drawable.ic_repeat),
                    background = LightYellow,
                    isUndo = true,
                    onSwipe = {
                        if (CardFace.Back == cardFace) {
                            indexList++
                            cardFace = cardFace.next
                        }
                        Toast.makeText(
                            mLocalContext,
                            "Повторить снова",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )

                SwipeableActionsBox(
                    modifier = Modifier.padding(top = 150.dp),
                    startActions = listOf(actionLearnedWord, actionDeleteWord),
                    endActions = listOf(actionRepeatWord),
                    backgroundUntilSwipeThreshold = Color.White
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
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
                                FrontFaceCard(listLearnWords, indexList)
                            },
                            back = {
                                BackFaceCard(listLearnWords, indexList)
                            },
                        )
                    }
                }
            }
        }
    }
}