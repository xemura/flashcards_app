package com.xenia.englishusingflashcards.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xenia.englishusingflashcards.room.entities.Category

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = NavigationItem.Splash.route,
    splashScreenContent: @Composable (NavHostController) -> Unit,
    mainScreenContent: @Composable (NavHostController) -> Unit,
    learningScreenContent: @Composable (NavHostController) -> Unit,
    categoryScreenContent: @Composable (NavHostController) -> Unit,
    createCategoryScreenContent: @Composable (NavHostController) -> Unit,
    editCategoryScreenContent: @Composable (NavHostController, Category) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            splashScreenContent(navHostController)
        }
        composable(NavigationItem.Main.route) {
            mainScreenContent(navHostController)
        }

        categoryScreenNavGraph(
            navController = navHostController,
            categoryScreenContent = categoryScreenContent,
            createCategoryScreenContent = createCategoryScreenContent,
            editCategoryScreenContent = editCategoryScreenContent
        )

        composable(NavigationItem.LearningCard.route) { //
            learningScreenContent(navHostController)
        }
    }
}