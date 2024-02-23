package com.xenia.englishusingflashcards.presentation.main_screen.notification_dialog

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.xenia.englishusingflashcards.alarm_manager.AlarmReceiver
import com.xenia.englishusingflashcards.data.prefsstore.NotificationRepository
import com.xenia.englishusingflashcards.presentation.MainActivity
import java.util.Calendar

private const val FILE_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FILE_PREFERENCE_NAME
)

class NotificationClass: Application() {

    lateinit var notificationPreferencesRepository: NotificationRepository

    override fun onCreate() {
        super.onCreate()
        notificationPreferencesRepository = NotificationRepository(dataStore)

        createNotificationChannel()

//        val intent = Intent(this, AlarmReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(
//            this, 0, intent, PendingIntent.FLAG_MUTABLE
//        )
//        val alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//
//        val calendar: Calendar = Calendar.getInstance()
//        val setCalendar: Calendar = Calendar.getInstance()
//        setCalendar.set(Calendar.HOUR_OF_DAY, 17)
//        setCalendar.set(Calendar.MINUTE, 46)
//        setCalendar.set(Calendar.SECOND, 0)
//
//        if (setCalendar.before(calendar))
//            setCalendar.add(Calendar.DATE, 1)
//
//        alarmManager.setInexactRepeating(
//            AlarmManager.RTC_WAKEUP,
//            System.currentTimeMillis() + 5000,
//            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
//            pendingIntent
//        )
    }

    private fun createNotificationChannel() {
        val name = "Напоминания о практике"
        val descriptionText = "MyApp Notifications"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("notify", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}