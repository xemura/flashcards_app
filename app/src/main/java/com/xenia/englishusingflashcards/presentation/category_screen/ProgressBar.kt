package com.xenia.englishusingflashcards.presentation.category_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp

private val progressBarColors = listOf(
    Color(0xFF184E77),
    Color(0xFF1E6091),
    Color(0xFF1A759F),
    Color(0xFF168AAD),
    Color(0xFF4DA2C7),
    Color(0xFF4DBFC7),
    Color(0xFF7DD8C7),
    Color(0xFFA2E3C8),
    Color(0xFFB8E7C8),
    Color(0xFFD3EBCD),
)

@Composable
fun SetProgressBar(percent : Int) {

    val modifier = Modifier
        .clip(shape = RoundedCornerShape(25.dp))
        .height(10.dp)

    val brush = Brush.linearGradient(
        colors = progressBarColors,
        tileMode = TileMode.Mirror
    )

    val width = 200.dp

    Box(
        modifier = modifier
            .background(Color.LightGray)
            .width(width)
    ) {
        Box(
            modifier = modifier
                .background(brush)
                .width(width * percent / 100)
        )
    }
}