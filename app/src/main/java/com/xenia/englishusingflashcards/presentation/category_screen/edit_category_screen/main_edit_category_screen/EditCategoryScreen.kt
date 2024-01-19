package com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen.main_edit_category_screen

import android.app.Activity
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.presentation.viewmodels.EditCategoryViewModel
import com.xenia.englishusingflashcards.presentation.viewmodels.EditCategoryViewModelFactory
import com.xenia.englishusingflashcards.room.entities.Category

@Composable
fun EditCategoryScreen(navController : NavController, category: Category) {

    val activity = (LocalContext.current as? Activity)
    val editCategoryViewModel: EditCategoryViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "EditCategoryViewModel",
        EditCategoryViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Header(contentPadding, "Редактировать", "to_category_screen_from_edit", navController, activity)

            ContentEditCategoryScreen(editCategoryViewModel, category.categoryName, category.image)
        }
    }
}
