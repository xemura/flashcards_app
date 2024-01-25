package com.xenia.englishusingflashcards.presentation.category_screen.category_list_screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.navigation.NavigationItem
import com.xenia.englishusingflashcards.data.entities.Category

@Composable
fun CategoryListItem(category: Category, navController : NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
            )
            .clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 20.dp),
            //.padding(start = 20.dp, end = 20.dp, ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically)
        {
            Image(painter = painterResource(id = category.image), contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .size(70.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .weight(0.5f)
            )


            Text(
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp)
                    .weight(1.4f),
                text = category.categoryName,
                fontSize = 20.sp
            )

            //SetProgressBar(category.progress.toInt())


            IconButton(
                onClick = {
                    navController.navigate(NavigationItem.EditCategory.getRouteWithArgs(category)) {
                        Log.d("Tag", "to edit")
                        popUpTo(NavigationItem.Category.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .size(50.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(25.dp))
                    .weight(0.3f)
            ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
            }
        }
    }
}