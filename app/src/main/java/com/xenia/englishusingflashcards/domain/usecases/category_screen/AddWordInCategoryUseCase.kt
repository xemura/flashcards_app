package com.xenia.englishusingflashcards.domain.usecases.category_screen

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.WordRepository

class AddWordInCategoryUseCase(private val wordRepository: WordRepository) {
    fun addWordInCategory(word: WordModel) {
        wordRepository.insertWord(word)
    }
}