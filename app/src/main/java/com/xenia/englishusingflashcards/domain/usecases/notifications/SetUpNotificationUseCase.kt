package com.xenia.englishusingflashcards.domain.usecases.notifications

import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class SetUpNotificationUseCase(private val categoryRepository: CategoryRepository) {
    fun deleteWordFromCategory() {
        categoryRepository.setUpNotification()
    }
}