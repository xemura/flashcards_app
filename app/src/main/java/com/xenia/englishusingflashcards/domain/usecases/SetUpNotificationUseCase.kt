package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class SetUpNotificationUseCase(private val categoryRepository: CategoryRepository) {
    fun deleteWordFromCategoryCategory() {
        categoryRepository.setUpNotification()
    }
}