package com.xenia.englishusingflashcards.presentation

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
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
import com.xenia.englishusingflashcards.alarm_manager.AlarmReceiver
import com.xenia.englishusingflashcards.navigation.AppNavGraph
import com.xenia.englishusingflashcards.presentation.category_screen.category_list_screen.CategoryScreen
import com.xenia.englishusingflashcards.presentation.category_screen.create_category_screen.CreateCategoryScreen
import com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen.main_edit_category_screen.EditCategoryScreen
import com.xenia.englishusingflashcards.presentation.learning_screen.LearningScreen
import com.xenia.englishusingflashcards.presentation.main_screen.MainScreen
import com.xenia.englishusingflashcards.presentation.main_screen.RuntimePermissionsDialog
import com.xenia.englishusingflashcards.ui.theme.EnglishUsingFlashcardsTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EnglishUsingFlashcardsTheme {

                WindowCompat.setDecorFitsSystemWindows(window, false)

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    RuntimePermissionsDialog(
                        Manifest.permission.POST_NOTIFICATIONS,
                        onPermissionDenied = {},
                        onPermissionGranted = {},
                    )
                }

                val intent = Intent(this, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(
                    this, 0, intent, PendingIntent.FLAG_MUTABLE
                )
                val alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                calendar.set(Calendar.HOUR_OF_DAY, 3)
                calendar.set(Calendar.MINUTE, 25)
                calendar.set(Calendar.SECOND, 0)

                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                    pendingIntent
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