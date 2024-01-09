package com.xenia.englishusingflashcards.presentation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.navigation.NavigationItem

@Composable
fun Header(contentPadding: PaddingValues, text: String, action: String, navController: NavController, activity: Activity?) {
    Box(
        modifier = Modifier
            .fillMaxWidth().padding(top = 15.dp)
            .padding(contentPadding.calculateBottomPadding().minus(10.dp))
    ) {
        IconButton(
            onClick = {
                action(action, navController, activity)
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 7.dp),
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp
        )
    }
}

fun action(action: String, navController: NavController, activity: Activity?) {
    when (action) {
        "close_app" -> activity?.finish()
        "to_main_screen" -> {
            navController.navigate(NavigationItem.Main.route) {
                popUpTo(NavigationItem.Category.route) {
                    inclusive = true
                }
            }
        }
        "to_category_screen" -> {
            navController.navigate(NavigationItem.Category.route) {
                popUpTo(NavigationItem.CreateCategory.route) {
                    inclusive = true
                }
            }
        }
    }
}