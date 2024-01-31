package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.WordRepository

class AddWordInCategoryUseCase(private val wordRepository: WordRepository) {
    fun addWordInCategory(word: WordModel) {
        wordRepository.insertWord(word)
    }
}