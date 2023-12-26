package com.xenia.englishusingflashcards.screens.learning_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.navigation.NavigationItem

@Composable
fun LearningScreen(navController : NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Learning Screen",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                navController.navigate(NavigationItem.Main.route) {
                    popUpTo(NavigationItem.LearningCard.route) {
                        inclusive = true
                    }
                }
            }
        ) {
            Text(text = "Main Screen")
        }
    }
}

@Preview
@Composable
fun PreviewLearningScreen() {
    LearningScreen(navController = rememberNavController())
}