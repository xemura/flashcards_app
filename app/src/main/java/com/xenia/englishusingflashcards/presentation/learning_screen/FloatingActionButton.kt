package com.xenia.englishusingflashcards.presentation.learning_screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButton() {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(25.dp))
            .clickable {

            },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = Icons.Filled.Info, contentDescription = "Info", modifier = Modifier.size(50.dp))
    }
}