package com.xenia.englishusingflashcards.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.xenia.englishusingflashcards.presentation.learning_screen.LearningScreen
import com.xenia.englishusingflashcards.presentation.main_screen.MainScreen
import com.xenia.englishusingflashcards.presentation.SplashScreen
import com.xenia.englishusingflashcards.presentation.category_screen.category_list_screen.CategoryScreen
import com.xenia.englishusingflashcards.presentation.category_screen.create_category_screen.CreateCategoryScreen
import com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen.main_edit_category_screen.EditCategoryScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationItem.Main.route) {
            MainScreen(navController)
        }
        composable(NavigationItem.LearningCard.route) {
            LearningScreen(navController)
        }
        composable(NavigationItem.Category.route) {
            CategoryScreen(navController)
        }
        composable(NavigationItem.CreateCategory.route) {
            CreateCategoryScreen(navController)
        }

        composable(NavigationItem.EditCategory.route + "/{categoryName}/{categoryImage}",
            arguments = listOf(
                navArgument("categoryName") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("categoryImage") {
                    type = NavType.IntType
                    nullable = false
                },
            )
        ) { entry ->
            EditCategoryScreen(
                navController,
                entry.arguments!!.getString("categoryName")!!,
                entry.arguments!!.getInt("categoryImage")
            )
        }
    }
}