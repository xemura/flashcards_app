package com.xenia.englishusingflashcards.domain.usecases.learn_screen

import com.xenia.englishusingflashcards.domain.repository.LearnRepository

class DeleteWordFromStudyTableUseCase(private val learnRepository: LearnRepository) {
    fun deleteWordInStudyTable(word: String, translate: String, sentence: String) {
        learnRepository.deleteWordFromStudyTable(word, translate, sentence)
    }
}