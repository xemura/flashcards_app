package com.xenia.englishusingflashcards.presentation.category_screen.category_sort_words_screen

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.presentation.category_screen.category_sort_words_screen.component_swipe_card.SwipeableCard
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word

@Composable
fun SortingWordsInCategoryScreen(navController : NavController, category: Category) {
//    val activity = (LocalContext.current as? Activity)
//
//    val sortWordsViewModel: SortWordsInCategoryViewModel = viewModel(
//        LocalViewModelStoreOwner.current!!,
//        "SortWordsInCategoryViewModel",
//        SortWordsInCategoryViewModelFactory(
//            LocalContext.current.applicationContext
//                    as Application
//        )
//    )
//
//    sortWordsViewModel.getListWordsInCategory(category.categoryName)
//
//    val wordsInCategory =
//        sortWordsViewModel.listWordInCategory.observeAsState()
//
//    Scaffold() { contentPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            Header(contentPadding, "Коллекция слов", "to_category_screen_from_sort", navController, activity)
//
//            val mList : MutableList<Word>? = wordsInCategory.value?.toMutableList()
//
//
//            Spacer(modifier = Modifier.height(50.dp))
//            Box(
//                modifier = Modifier
//                    .padding(20.dp),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                SwipeableCard(
//                    dataSource = (0..10).map { 0 }.toList()
//                )
//            }
//
//            Log.d("Tag", category.categoryName)
//            Log.d("Tag", wordsInCategory.value.toString())
//        }
//    }
}

@Preview
@Composable
fun PreviewSortWordsInCategoryScreen() {
    SortingWordsInCategoryScreen(navController = rememberNavController(), Category(0, "", 0))
}