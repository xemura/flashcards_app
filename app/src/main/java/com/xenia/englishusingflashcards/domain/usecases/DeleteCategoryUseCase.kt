package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class DeleteCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun deleteCategory(category: Category) {
        categoryRepository.deleteCategory(category)
    }
}