package com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen.main_edit_category_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen.EditScreenCardAddWordInCategory
import com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen.EditScreenCardEditCategory
import com.xenia.englishusingflashcards.presentation.viewmodels.EditCategoryViewModel

@Composable
fun ContentEditCategoryScreen (
    editCategoryViewModel: EditCategoryViewModel,
    categoryImage: Int
) {
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.3f)
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 10.dp
            )
            .background(color = Color.White)
            .clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
            contentAlignment = Alignment.Center
        ) {
            editCategoryViewModel.also {
                it.updateCategoryImage(categoryImage)

                EditScreenCardEditCategory(it)
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 10.dp
            )
            .background(color = Color.White)
            .clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
            contentAlignment = Alignment.Center
        ) {
            EditScreenCardAddWordInCategory(editCategoryViewModel)
        }
    }
}