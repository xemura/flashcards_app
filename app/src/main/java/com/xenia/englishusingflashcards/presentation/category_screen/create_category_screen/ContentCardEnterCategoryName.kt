package com.xenia.englishusingflashcards.presentation.category_screen.create_category_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.presentation.viewmodels.CreateCategoryViewModel


var imagesList = listOf(
    R.drawable.image_1,
    R.drawable.image_2,
    R.drawable.image_3,
    R.drawable.image_4,
)

@Composable
fun CardEnterCategoryName(createCategoryViewModel: CreateCategoryViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {
            createCategoryViewModel.updateCategoryImage(imagesList.random())
        }, modifier = Modifier.size(80.dp)) {
            Image(
                painter = painterResource(id = createCategoryViewModel.categoryImage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
        }

        StyledTextField()
    }
}