package com.xenia.englishusingflashcards.presentation.main_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.navigation.NavigationItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.xenia.englishusingflashcards.presentation.Header

private val backgroundColors = listOf(
    Color(0xFF03045E),
    Color(0xFF0077B6),
    Color(0xFF00B4D8),
    Color(0xFF90E0EF),
    Color(0xFFCAF0F8),
)

@Composable
fun MainScreen(navController : NavController) {
    val activity = (LocalContext.current as? Activity)
    // val boxSize = with(LocalDensity.current) { 200.dp.toPx() }
    Scaffold(
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
//            .background(
//                brush = Brush.linearGradient(
//                    colors = backgroundColors,
//                    start = Offset(0f, 0f), // top left corner
//                    end = Offset(boxSize, boxSize) // bottom right corner
//                )
//            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Header(contentPadding, "Главная", "close_app", navController, activity)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Привет!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Card("0", "учить")
                Card("0", "знаю")
                Card("0", "выучено")
            }

            Spacer(modifier = Modifier.height(136.dp))

            AlertDialogPlayground()
            ButtonMain(navController, NavigationItem.CategoryMain.route, "Категории")
            ButtonMain(navController, NavigationItem.LearningCard.route, "Обучение")

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "created by xemura",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 15.sp
            )
        }
    }
}