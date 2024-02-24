package com.xenia.englishusingflashcards.presentation

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.xenia.englishusingflashcards.alarm_manager.AlarmReceiver
import com.xenia.englishusingflashcards.navigation.AppNavGraph
import com.xenia.englishusingflashcards.presentation.category_screen.category_list_screen.CategoryScreen
import com.xenia.englishusingflashcards.presentation.category_screen.create_category_screen.CreateCategoryScreen
import com.xenia.englishusingflashcards.presentation.category_screen.edit_category_screen.main_edit_category_screen.EditCategoryScreen
import com.xenia.englishusingflashcards.presentation.learning_screen.LearningScreen
import com.xenia.englishusingflashcards.presentation.main_screen.MainScreen
import com.xenia.englishusingflashcards.presentation.main_screen.RuntimePermissionsDialog
import com.xenia.englishusingflashcards.presentation.viewmodels.NotificationViewModel
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

                val notificationViewModel: NotificationViewModel = viewModel(
                    factory = NotificationViewModel.Factory
                )

                val time = notificationViewModel.time.collectAsState()

                Log.d("Notification", time.value)

                val alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(
                    this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    RuntimePermissionsDialog(
                        Manifest.permission.POST_NOTIFICATIONS,
                        onPermissionDenied = {},
                        onPermissionGranted = {},
                    )

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
                        Log.d("Notification", "not allow")
                        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                        intent.setData(Uri.fromParts("package", packageName, null))
                        startActivity(intent)
                    }
                }

                val calendarInstance = Calendar.getInstance()
                calendarInstance.timeInMillis = System.currentTimeMillis()
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()

                val timeString = time.value
                val hour = timeString.substring(0, 2)
                val minutes = timeString.substring(3, 5)
                Log.d("Notification", hour.toInt().toString())
                Log.d("Notification", minutes.toInt().toString())

//                calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
//                calendar.set(Calendar.MINUTE, minutes.toInt())
//                calendar.set(Calendar.SECOND, 0)

                calendar.set(Calendar.HOUR_OF_DAY, 18)
                calendar.set(Calendar.MINUTE, 30)
                calendar.set(Calendar.SECOND, 0)

//                calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
//                calendar.set(Calendar.MINUTE, minutes.toInt())
//                calendar.set(Calendar.SECOND, 0)

                //val alarmClock = AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent)

                // alarmManager.setAlarmClock(alarmClock, pendingIntent)

                // calendar.timeInMillis = System.currentTimeMillis() + calendar.timeInMillis
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + AlarmManager.INTERVAL_HALF_DAY,
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