package com.xenia.englishusingflashcards.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.xenia.englishusingflashcards.data.entities.Category

fun NavGraphBuilder.categoryScreenNavGraph(
    navController: NavHostController,
    categoryScreenContent: @Composable (NavHostController) -> Unit,
    createCategoryScreenContent: @Composable (NavHostController) -> Unit,
    editCategoryScreenContent: @Composable (NavHostController, Category) -> Unit,
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
    }
}