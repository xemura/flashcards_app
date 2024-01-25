package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class UpdateCategoryNameUseCase(private val categoryRepository: CategoryRepository) {
    fun updateCategoryName(oldName: String, newName: String) {
        categoryRepository.updateCategoryName(oldName, newName)
    }
}