package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.LearnRepository

class AddWordInStudyTableUseCase(private val learnRepository: LearnRepository) {
    fun addWordInStudyTable(word: WordModel) {
        learnRepository.addWordInStudyTable(word)
    }
}