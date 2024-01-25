package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.repository.CategoryRepository

class DeleteWordFromCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun deleteWordFromCategory(categoryName: String, word: String, translate: String, sentence: String) {
        categoryRepository.deleteWordFromCategory(
            categoryName = categoryName,
            word = word,
            translate = translate,
            sentence = sentence
        )
    }
}