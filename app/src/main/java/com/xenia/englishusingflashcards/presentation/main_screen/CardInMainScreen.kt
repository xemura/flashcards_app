package com.xenia.englishusingflashcards.presentation.main_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.presentation.main_screen.bottom_sheets.BottomSheet

private val TextColors = listOf(
    Color(0xFF03045E),
    Color(0xFF0077B6),
    Color(0xFF00B4D8),
    Color(0xFF90E0EF),
    Color(0xFFCAF0F8),
)

@Composable
fun CardInMainScreen(countWordsText : Int, cardText: String) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet(cardText) {
            showSheet = false
        }
    }

    Column {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp))
            .background(Color.White)
            .height(100.dp)
            .width(100.dp)
        ) {
            IconButton(
                onClick = {
                    showSheet = true
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                countWordsText.toString(),
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = TextColors,
                        tileMode = TileMode.Mirror
                    ),
                    fontSize = 45.sp,
                    fontFamily = FontFamily(Font(R.font.comfortaa_regular))
                ),
                modifier = Modifier.align(Alignment.Center).padding(top = 10.dp)
            )
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = cardText,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 15.sp
        )
    }
}