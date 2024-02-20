package com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xenia.englishusingflashcards.ui.theme.LightGrayCustom
import com.xenia.englishusingflashcards.presentation.viewmodels.EditCategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenCardAddWordInCategory(editCategoryViewModel: EditCategoryViewModel) {

    val wordsInCreatedCategory =
        editCategoryViewModel.listWordInCategory.collectAsState()

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Слова в категории",
            modifier = Modifier
                .padding(top = 10.dp)
                .weight(0.08f))

        if (!wordsInCreatedCategory.value.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
            ) {
                items(
                    items = wordsInCreatedCategory.value!!,
                    key = { word ->
                        word.sentence
                    }
                ) { (_, _, word, translate, sentence) ->

                    val dismissState = rememberDismissState()

                    if (dismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                        editCategoryViewModel.deleteWordInCategory(editCategoryViewModel.categoryName, word, translate, sentence)
                    }

                    SwipeToDismiss(
                        modifier = Modifier.padding(vertical = 10.dp),
                        state = dismissState,
                        directions = setOf(
                            DismissDirection.EndToStart,
                        ),
                        background = {
                            // background color
                            val backgroundColor by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.DismissedToStart -> Color.Red.copy(alpha = 0.6f)
                                    else -> Color.White
                                }, label = ""
                            )

                            // icon
                            val iconImageVector = Icons.Outlined.Delete

                            // icon placement
                            val iconAlignment = when (dismissState.targetValue) {
                                DismissValue.DismissedToEnd -> Alignment.CenterStart
                                else -> Alignment.CenterEnd
                            }

                            // icon size
                            val iconScale by animateFloatAsState(
                                targetValue = if (dismissState.targetValue == DismissValue.Default) 0.5f else 1.3f,
                                label = ""
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color = backgroundColor)
                                    .padding(start = 16.dp, end = 16.dp),
                                contentAlignment = iconAlignment
                            ) {
                                Icon(
                                    modifier = Modifier.scale(iconScale),
                                    imageVector = iconImageVector,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        },
                        dismissContent = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                                    .clip(RoundedCornerShape(25.dp))
                                    .background(LightGrayCustom),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp, vertical = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(text = word, textAlign = TextAlign.Center)
                                    Text(text = translate, textAlign = TextAlign.Center)
                                    Text(text = sentence, textAlign = TextAlign.Center)
                                }
                            }
                        }
                    )


                }
            }
        } else {
            Text(text = "Нет слов в категории. Добавьте.",
                modifier = Modifier.weight(0.7f))
        }

        AlertDialogAddWordInEditCategoryPlayground(editCategoryViewModel)
    }
}