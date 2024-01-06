package com.xenia.englishusingflashcards.presentation.category_screen

import android.app.Application
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.navigation.NavigationItem
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModel
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModelFactory

@Composable
fun CategoryScreen(navController : NavController) {
    val categoryViewModel: CategoryViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "CategoryViewModel",
        CategoryViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    val data = categoryViewModel.getCategories()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                navController = navController
            )
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
                    text = "Категории",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp
                )
            }

            Log.d("CategoryScreen", data.toString())
            if (!data.isNullOrEmpty()) {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()) {
                    items(data) { (id, categoryName, image, percent) ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 20.dp,
                                    end = contentPadding.calculateBottomPadding(),
                                    bottom = 10.dp
                                )
                                .clip(RoundedCornerShape(25.dp))
                                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp),
                                horizontalArrangement = Arrangement.Start) {
                                Image(painter = painterResource(id = R.drawable.test_compressed), contentDescription = "",
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

                                    SetProgressBar(percent.toInt())
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
            else {
                Text(text = "Нет категорий. Создайте.")
            }

        }
    }
}

@Preview
@Composable
fun PreviewCategoryScreen() {
    CategoryScreen(navController = rememberNavController())
}