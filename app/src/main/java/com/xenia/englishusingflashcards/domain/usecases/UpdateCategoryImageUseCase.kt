package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class UpdateCategoryImageUseCase(private val categoryRepository: CategoryRepository) {
    fun updateCategoryImage(oldImage: Int, newImage: Int, categoryName: String) {
        categoryRepository.updateCategoryImage(oldImage, newImage, categoryName)
    }
}