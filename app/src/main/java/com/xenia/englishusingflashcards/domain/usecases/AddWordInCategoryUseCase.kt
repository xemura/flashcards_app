package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.domain.repository.WordRepository

class AddWordInCategoryUseCase(private val wordRepository: WordRepository) {
    fun addWordInCategory(word: Word) {
        wordRepository.insertWord(word)
    }
}