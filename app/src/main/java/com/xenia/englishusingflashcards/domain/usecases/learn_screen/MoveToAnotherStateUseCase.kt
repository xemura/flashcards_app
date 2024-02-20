package com.xenia.englishusingflashcards.domain.usecases.learn_screen

import com.xenia.englishusingflashcards.domain.repository.LearnRepository

class MoveToAnotherStateUseCase(private val learnRepository: LearnRepository) {
    fun guessedCardAndMoveToKnowState(wordId: Int) {
        return learnRepository.guessedCardAndMoveToKnowState(wordId)
    }
}