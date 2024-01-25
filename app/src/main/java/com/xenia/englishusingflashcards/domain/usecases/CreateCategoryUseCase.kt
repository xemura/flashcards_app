package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun createCategory(category: Category, listWordsInCategory: List<Word>?) {
        categoryRepository.createCategory(category, listWordsInCategory)
    }
}