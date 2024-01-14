package com.xenia.englishusingflashcards.presentation.edit_category_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.viewmodels.EditCategoryViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

var imagesList = listOf(
    R.drawable.image_1,
    R.drawable.image_2,
    R.drawable.image_3,
    R.drawable.image_4
)

@Composable
fun EditScreenCardEditCategory(editCategoryViewModel: EditCategoryViewModel) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        IconButton(onClick = {
            val newImage = imagesList.random()
            editCategoryViewModel.updateImageCategory(
                editCategoryViewModel.categoryImage,
                newImage,
                editCategoryViewModel.categoryName
            )
            editCategoryViewModel.updateCategoryImage(newImage)
        }, modifier = Modifier.size(80.dp)) {
            Image(
                painter = painterResource(id = editCategoryViewModel.categoryImage
                    //editCategoryViewModel.categoryImage
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
        }

        EditStyledTextField(editCategoryViewModel)
    }
}