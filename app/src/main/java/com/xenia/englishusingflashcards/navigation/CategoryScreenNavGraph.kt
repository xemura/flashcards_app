package com.xenia.englishusingflashcards.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.xenia.englishusingflashcards.room.entities.Category


fun NavGraphBuilder.categoryScreenNavGraph(
    navController: NavHostController,
    categoryScreenContent: @Composable (NavHostController) -> Unit,
    createCategoryScreenContent: @Composable (NavHostController) -> Unit,
    editCategoryScreenContent: @Composable (NavHostController, Category) -> Unit,
    wordsCollectionCategoryScreenContent: @Composable (NavHostController, Category) -> Unit,
) {
    navigation(
        startDestination = NavigationItem.CategoryMain.route,
        route = NavigationItem.Category.route
    ) {
        composable(NavigationItem.CategoryMain.route) {
            categoryScreenContent(navController)
        }

        composable(NavigationItem.CreateCategory.route) {
            createCategoryScreenContent(navController)
        }

        composable(
            route = NavigationItem.EditCategory.route,
            arguments = listOf(
                navArgument("edit_category") {
                    type = NavType.StringType
                },
            )
        ) {
            val editCategoryJson = it.arguments?.getString("edit_category") ?: ""
            val editCategory = Gson().fromJson(editCategoryJson, Category::class.java)
            editCategoryScreenContent(navController, editCategory)
        }

        composable(
            route = NavigationItem.WordsCategory.route,
            arguments = listOf(
                navArgument("words_category") {
                    type = NavType.StringType
                },
            )
        ) {
            val wordsCategoryJson = it.arguments?.getString("words_category") ?: ""
            val wordsCategory = Gson().fromJson(wordsCategoryJson, Category::class.java)
            wordsCollectionCategoryScreenContent(navController, wordsCategory)
        }
    }
}