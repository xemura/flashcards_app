package com.xenia.englishusingflashcards.domain.usecases


import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class DeleteCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun deleteCategory(category: CategoryModel) {
        categoryRepository.deleteCategory(category)
    }
}