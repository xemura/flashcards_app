package com.xenia.englishusingflashcards.presentation.main_screen.notification_dialog

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.xenia.englishusingflashcards.prefsstore.NotificationRepository

private const val FILE_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FILE_PREFERENCE_NAME
)

/*
 * Custom app entry point for manual dependency injection
 */
class NotificationClass: Application() {
    lateinit var notificationPreferencesRepository: NotificationRepository

    override fun onCreate() {
        super.onCreate()
        notificationPreferencesRepository = NotificationRepository(dataStore)
    }
}