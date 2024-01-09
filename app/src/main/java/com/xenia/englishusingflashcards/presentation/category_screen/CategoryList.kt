package com.xenia.englishusingflashcards.presentation.category_screen

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModel
import com.xenia.englishusingflashcards.viewmodels.CategoryViewModelFactory


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

    Log.d("CategoryScreen", data.toString())
    if (!data.value.isNullOrEmpty()) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()) {
            items(data.value!!) { (_, categoryName, image, percent) ->
                CategoryListItem(image = image, categoryName = categoryName, percent = percent)
            }
        }
    }
    else {
        Text(text = "Нет категорий. Создайте.")
    }
}