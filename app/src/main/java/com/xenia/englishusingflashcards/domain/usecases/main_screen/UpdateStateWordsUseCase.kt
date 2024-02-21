package com.xenia.englishusingflashcards.domain.usecases.main_screen

import com.xenia.englishusingflashcards.domain.repository.LearnRepository

class UpdateStateWordsUseCase(private val learnRepository: LearnRepository) {
    fun updateStateWords() {
        return learnRepository.updateStateWords()
    }
}