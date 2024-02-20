package com.xenia.englishusingflashcards.domain.usecases.category_screen

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetWordsFromCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun getWordsFromCategory(categoryName: String): Flow<List<WordModel>?> {
        return categoryRepository.getWordsFromCategory(categoryName)
    }
}