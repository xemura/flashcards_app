package com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.presentation.viewmodels.EditCategoryViewModel

var imagesList = listOf(
    R.drawable.image_1,
    R.drawable.image_2,
    R.drawable.image_3,
    R.drawable.image_4
)

@Composable
fun EditScreenCardEditCategory(editCategoryViewModel: EditCategoryViewModel) {

    val categoryImage = editCategoryViewModel.categoryImage.observeAsState(0)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        IconButton(onClick = {
            val newImage = imagesList.random()
            editCategoryViewModel.updateImageCategory(
                categoryImage.value,
                newImage,
                editCategoryViewModel.categoryName
            )
            editCategoryViewModel.updateCategoryImage(newImage)
        }, modifier = Modifier.size(80.dp)) {
            Image(
                painter = painterResource(
                    id = categoryImage.value
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
        }

        Text(text = editCategoryViewModel.categoryName,
            fontSize = 30.sp)
    }
}