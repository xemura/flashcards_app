package com.xenia.englishusingflashcards

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.navigation.AppNavGraph
import com.xenia.englishusingflashcards.presentation.SplashScreen
import com.xenia.englishusingflashcards.presentation.category_screen.category_list_screen.CategoryScreen
import com.xenia.englishusingflashcards.presentation.category_screen.create_category_screen.CreateCategoryScreen
import com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen.main_edit_category_screen.EditCategoryScreen
import com.xenia.englishusingflashcards.presentation.learning_screen.LearningScreen
import com.xenia.englishusingflashcards.presentation.main_screen.MainScreen
import com.xenia.englishusingflashcards.ui.theme.EnglishUsingFlashcardsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishUsingFlashcardsTheme {
                // transparent status bar (invisible)
                WindowCompat.setDecorFitsSystemWindows(window, false)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph(
                        navHostController = rememberNavController(),
                        splashScreenContent = { navController ->
                            SplashScreen(navController = navController)
                        },
                        mainScreenContent = { navController ->
                            MainScreen(navController = navController)
                        },
                        learningScreenContent = { navController ->
                            LearningScreen(navController = navController)
                        },
                        categoryScreenContent = { navController ->
                            CategoryScreen(navController = navController)
                        },
                        editCategoryScreenContent = { navController, category ->
                            EditCategoryScreen(
                                navController = navController,
                                category = category
                            )
                        },
                        createCategoryScreenContent = { navController ->
                            CreateCategoryScreen(navController = navController)
                        }
                    )
                }
            }
        }
    }
}