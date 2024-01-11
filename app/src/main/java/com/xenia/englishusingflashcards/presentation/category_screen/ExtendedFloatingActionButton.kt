package com.xenia.englishusingflashcards.presentation.category_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.navigation.NavigationItem

@Composable
fun ExtendedFloatingActionButton(navController : NavController) {
    androidx.compose.material3.ExtendedFloatingActionButton(
        modifier = Modifier.size(70.dp)
            .clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
        onClick = {
            navController.navigate(NavigationItem.CreateCategory.route) {
                popUpTo(NavigationItem.Category.route) {
                    inclusive = true
                }
            }
        },
        containerColor = Color(202, 240, 248, 255),
        contentColor = Color.Black
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}