package com.xenia.englishusingflashcards.data.prefsstore


import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class NotificationRepository(private val dataStore: DataStore<Preferences>) {

    private companion object {
        private val KEY_NOTIFICATION_TIME = stringPreferencesKey("notification_time")
        private val KEY_NOTIFICATION_STATE = booleanPreferencesKey("notification_state")
    }

    suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun saveNotificationState(value: Boolean) {
        dataStore.setValue(KEY_NOTIFICATION_STATE, value)
    }

    suspend fun saveNotificationTime(value: String) {
        dataStore.setValue(KEY_NOTIFICATION_TIME, value)
    }

    val state: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e("TAG", "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[KEY_NOTIFICATION_STATE] ?: false
        }

    val time: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e("TAG", "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[KEY_NOTIFICATION_TIME] ?: "08:00"
        }


}