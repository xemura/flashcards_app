package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class GetWordsFromCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun getWordsFromCategoryCategory(categoryName: String): List<WordModel>? {
        return categoryRepository.getWordsFromCategory(categoryName)
    }
}