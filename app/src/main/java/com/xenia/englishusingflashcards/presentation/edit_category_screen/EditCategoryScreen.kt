package com.xenia.englishusingflashcards.presentation.edit_category_screen

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xenia.englishusingflashcards.navigation.NavigationItem
import com.xenia.englishusingflashcards.presentation.Header
import com.xenia.englishusingflashcards.viewmodels.EditCategoryViewModel
import com.xenia.englishusingflashcards.viewmodels.EditCategoryViewModelFactory

@Composable
fun EditCategoryScreen(navController : NavController, categoryName: String, categoryImage: Int) {
    val activity = (LocalContext.current as? Activity)

    val editCategoryViewModel: EditCategoryViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "EditCategoryViewModel",
        EditCategoryViewModelFactory(
            LocalContext.current.applicationContext
                    as Application
        )
    )

    editCategoryViewModel.updateCategoryName(categoryName)
    editCategoryViewModel.updateCategoryImage(categoryImage)

//    Log.d("Tag", "in fun ${editCategoryViewModel.categoryName}")
//    Log.d("Tag", "in fun ${editCategoryViewModel.categoryImage}")

    val oldName = editCategoryViewModel.categoryName
    Log.d("Tag", "in fun $oldName")
    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Header(contentPadding, "Редактировать", "to_category_screen", navController, activity)

            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 10.dp
                    )
                    .background(color = Color.White)
                    .clip(RoundedCornerShape(25.dp))
                    .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    EditScreenCardEditCategory(editCategoryViewModel)
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 10.dp
                    )
                    .background(color = Color.White)
                    .clip(RoundedCornerShape(25.dp))
                    .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    editCategoryViewModel.getListWordsInCategory(categoryName)
                    EditScreenCardAddWordInCategory(editCategoryViewModel)
                }
                Button(
                    onClick = {

                          // сохранить изменения в категории.
                          // возможно изменить картинку в категории, название категории
                          // а также слова в категории (удалить или добавить)
                        if (editCategoryViewModel.categoryName.isNotEmpty()) {
                            editCategoryViewModel.saveCategoryWithWords(oldName)
                            navController.navigate(NavigationItem.Category.route) {
                                popUpTo(NavigationItem.CreateCategory.route) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    Modifier
                        .fillMaxWidth()
                        .weight(0.16f)
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    shape = RoundedCornerShape(25.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(202, 240, 248, 255),
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        "Сохранить",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}
