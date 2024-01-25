package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class GetWordsFromCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun getWordsFromCategoryCategory(categoryName: String): List<Word>? {
        return categoryRepository.getWordsFromCategory(categoryName)
    }
}