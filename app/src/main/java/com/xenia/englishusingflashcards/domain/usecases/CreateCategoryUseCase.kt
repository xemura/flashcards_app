package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun createCategory(category: CategoryModel, listWordsInCategory: List<WordModel>?) {
        categoryRepository.createCategory(category, listWordsInCategory)
    }
}