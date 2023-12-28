package com.xenia.englishusingflashcards.presentation.main_screen.bottom_sheets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.englishusingflashcards.data.getBottomSheetInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(text : String, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        val bottomSheetInfo = getBottomSheetInfo(text)

        Text( modifier = Modifier
            .align(Alignment.CenterHorizontally),
            text = bottomSheetInfo.header,
            fontSize = 25.sp
        )

        Text( modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(horizontal = 20.dp),
            textAlign = TextAlign.Center,
            text = bottomSheetInfo.description,
            color = Color.Gray
        )

        LazyColumn {
            items(bottomSheetInfo.data) { (word, translate, sentence) ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = word,
                    )
                    Text(
                        text = translate,
                    )
                    Text(text = sentence)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

