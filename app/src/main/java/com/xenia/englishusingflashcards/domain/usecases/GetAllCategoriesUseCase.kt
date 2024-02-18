package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    fun getCategories() : Flow<List<CategoryModel>?> {
        return categoryRepository.getCategories()
    }
}