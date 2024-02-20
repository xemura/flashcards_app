package com.xenia.englishusingflashcards.presentation.learning_screen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButton() {

    val mLocalContext = LocalContext.current

    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(25.dp))
            .clickable {
                Toast
                    .makeText(
                        mLocalContext,
                        "Смахните влево для повторения слова, смахните вправо если вспомнили слово",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "Info",
            modifier = Modifier.size(50.dp)
        )
    }
}