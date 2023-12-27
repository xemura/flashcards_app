package com.xenia.englishusingflashcards.screens.category_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.database.categoryTest
import com.xenia.englishusingflashcards.navigation.NavigationItem

private val TextColors = listOf(
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
fun CategoryScreen(navController : NavController) {
    val data = categoryTest
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
                onClick = {
                    navController.navigate(NavigationItem.LearningCard.route) {
                        popUpTo(NavigationItem.Main.route) {
                            inclusive = true
                        }
                    }
                },
                containerColor = Color(202, 240, 248, 255),
                contentColor = Color.Black
            ) {
                Text(
                    "Учить",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(NavigationItem.Main.route) {
                            popUpTo(NavigationItem.Category.route) {
                                inclusive = true
                            }
                        }
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
                    text = "Главная",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp
                )
            }

            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                items(data) { (level, categoryName, image, words, percent) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = contentPadding.calculateBottomPadding(), bottom = 10.dp)
                            .clip(RoundedCornerShape(25.dp))
                            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                            horizontalArrangement = Arrangement.Start) {
                            Image(painter = painterResource(id = R.drawable.test), contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(25.dp)))
                            Column (modifier = Modifier.padding(start = 20.dp, top = 15.dp)) {
                                Text(
                                    modifier = Modifier.padding(bottom = 2.dp),
                                    text = categoryName,
                                    fontSize = 20.sp
                                )
                                CustomProgressBar(
                                    Modifier
                                        .clip(shape = RoundedCornerShape(25.dp))
                                        .height(10.dp),
                                    200.dp,
                                    Color.LightGray,
                                    Brush.linearGradient(
                                        colors = TextColors,
                                        tileMode = TileMode.Mirror
                                    ),
                                    percent.toInt()
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun PreviewCategoryScreen() {
    CategoryScreen(navController = rememberNavController())
}

@Composable
fun CustomProgressBar(
    modifier: Modifier, width: Dp, backgroundColor: Color, foregroundColor: Brush, percent: Int
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .width(width)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100)
        )
    }
}