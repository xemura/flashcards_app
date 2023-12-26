package com.xenia.englishusingflashcards.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xenia.englishusingflashcards.screens.learning_screen.LearningScreen
import com.xenia.englishusingflashcards.screens.main_screen.MainScreen
import com.xenia.englishusingflashcards.screens.SplashScreen
import com.xenia.englishusingflashcards.screens.category_screen.CategoryScreen

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
    }
}