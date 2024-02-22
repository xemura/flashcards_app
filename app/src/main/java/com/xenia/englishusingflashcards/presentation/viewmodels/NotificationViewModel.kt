package com.xenia.englishusingflashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xenia.englishusingflashcards.data.prefsstore.NotificationRepository
import com.xenia.englishusingflashcards.presentation.main_screen.notification_dialog.NotificationClass
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationRepository: NotificationRepository
): ViewModel() {

    var time: StateFlow<String> = notificationRepository.time.map { time ->
        time
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = "09:00"
    )

    var state: StateFlow<Boolean> = notificationRepository.state.map { state ->
        state
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    fun saveTimeNotification(time: String) {
        viewModelScope.launch {
            notificationRepository.saveNotificationTime(time)
        }
    }

    fun saveStateNotification(state: Boolean) {
        viewModelScope.launch {
            notificationRepository.saveNotificationState(state)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NotificationClass)
                NotificationViewModel(application.notificationPreferencesRepository)
            }
        }
    }
}