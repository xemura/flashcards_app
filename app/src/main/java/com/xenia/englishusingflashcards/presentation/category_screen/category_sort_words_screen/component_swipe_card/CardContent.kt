package com.xenia.englishusingflashcards.presentation.category_screen.category_sort_words_screen.component_swipe_card

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xenia.englishusingflashcards.presentation.category_screen.category_sort_words_screen.component_swipe_card.Constants.cornerRadiusBig
import com.xenia.englishusingflashcards.presentation.category_screen.category_sort_words_screen.component_swipe_card.Constants.normalElevation
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.ui.theme.default

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    cardIndex: Int
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadiusBig),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = cardIndex.toString(),
            )
            Text(
                text = cardIndex.toString(),
            )
            Text(
                text = cardIndex.toString(),
            )
        }
    }
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(
//                start = 20.dp,
//                end = 20.dp,
//            )
//            .clip(RoundedCornerShape(25.dp))
//            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp))
//            .background(Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Text(text = word.toString())
//        Text(text = word.translate)
//        Text(text = word.sentence)
//    }
}