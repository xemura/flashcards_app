package com.xenia.englishusingflashcards.presentation.category_screen

import android.app.Application
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModel
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryList() {

    val categoryViewModel: CategoryViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "CategoryViewModel",
        CategoryViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    val data = categoryViewModel.categories.collectAsState(initial = emptyList())

    if (!data.value.isNullOrEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxWidth())
        {
            items(
                items = data.value!!,
                key = { category ->
                    category.id
                }
            ) { category ->
                val dismissState = rememberDismissState()

                if (dismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                    // swiping from left to right
                    categoryViewModel.deleteCategory(category)
                    //peopleList.remove(people)
                }

                SwipeToDismiss(
                    modifier = Modifier.padding(bottom = 10.dp),
                    state = dismissState,
                    directions = setOf(
                        DismissDirection.EndToStart,
                    ),
                    background = {
                        // background color
                        val backgroundColor by animateColorAsState(
                            when (dismissState.targetValue) {
                                DismissValue.DismissedToStart -> Color.Red.copy(alpha = 0.6f)
                                else -> Color.White
                            }, label = ""
                        )

                        // icon
                        val iconImageVector = Icons.Outlined.Delete

                        // icon placement
                        val iconAlignment = when (dismissState.targetValue) {
                            DismissValue.DismissedToEnd -> Alignment.CenterStart
                            else -> Alignment.CenterEnd
                        }

                        // icon size
                        val iconScale by animateFloatAsState(
                            targetValue = if (dismissState.targetValue == DismissValue.Default) 0.5f else 1.3f,
                            label = ""
                        )

                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color = backgroundColor)
                                .padding(start = 16.dp, end = 16.dp), // inner padding
                            contentAlignment = iconAlignment
                        ) {
                            Icon(
                                modifier = Modifier.scale(iconScale),
                                imageVector = iconImageVector,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    dismissContent = {
                        // list item
                        CategoryListItem(image = category.image, categoryName = category.categoryName, percent = category.progress)
                    }
                )
            }
        }
    }
    else {
        Text(text = "Нет категорий. Создайте.")
    }
}