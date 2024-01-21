package com.xenia.englishusingflashcards.presentation.main_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.navigation.NavigationItem

@Composable
fun ButtonMain(navController: NavController, navigationItemRoute: String, text: String) {
    Button(
        onClick = {
            navController.navigate(navigationItemRoute) {
                popUpTo(NavigationItem.Main.route) {
                    inclusive = true
                }
            }
        },
        Modifier
            .fillMaxWidth()
            //.wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(202, 240, 248, 255),
            contentColor = Color.Black)
    ){
        Text(
            text,
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}