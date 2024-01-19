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
    wordsCollectionCategoryScreenContent: @Composable (NavHostController, Category) -> Unit,
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
        composable(NavigationItem.LearningCard.route) { //
            learningScreenContent(navHostController)
        }
//        composable(NavigationItem.Category.route) {
//            categoryScreenContent(navHostController)
//        }
//        composable(NavigationItem.CreateCategory.route) {
//            CreateCategoryScreen(navController)
//        }

        categoryScreenNavGraph(
            navController = navHostController,
            categoryScreenContent = categoryScreenContent,
            createCategoryScreenContent = createCategoryScreenContent,
            editCategoryScreenContent = editCategoryScreenContent,
            wordsCollectionCategoryScreenContent = wordsCollectionCategoryScreenContent
        )

//        composable(NavigationItem.EditCategory.route + "/{categoryName}/{categoryImage}",
//            arguments = listOf(
//                navArgument("categoryName") {
//                    type = NavType.StringType
//                    nullable = false
//                },
//                navArgument("categoryImage") {
//                    type = NavType.IntType
//                    nullable = false
//                },
//            )
//        ) { entry ->
//            EditCategoryScreen(
//                navController,
//                entry.arguments!!.getString("categoryName")!!,
//                entry.arguments!!.getInt("categoryImage")
//            )
//        }
    }
}