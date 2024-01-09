package com.xenia.englishusingflashcards.presentation.create_category_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.xenia.englishusingflashcards.ui.theme.LightGrayCustom
import com.xenia.englishusingflashcards.viewmodels.CreateCategoryViewModel

@Composable
fun ContentCardAddWordInCreateCategory(createCategoryViewModel: CreateCategoryViewModel) {

    val wordsInCreatedCategory =
        createCategoryViewModel.listWordInCategory.observeAsState()

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
                items(wordsInCreatedCategory.value!!) { (_, _, word, translate, sentence) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 20.dp)
                            .clip(RoundedCornerShape(25.dp))
                            .background(LightGrayCustom),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = word,
                        )
                        Text(
                            text = translate,
                        )
                        Text(text = sentence)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        } else {
            Text(text = "Нет слов в категории. Добавьте.",
                modifier = Modifier.weight(0.7f))
        }

        AlertDialogAddWordInCategoryPlayground()
    }
}