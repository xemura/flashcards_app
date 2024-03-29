package com.xenia.englishusingflashcards.domain.usecases.learn_screen

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.LearnRepository

class AddWordsInStudyTableUseCase(private val learnRepository: LearnRepository) {
    fun addWordsInStudyTable(listWords: List<WordModel>?) {
        learnRepository.addWordsInStudyTable(listWords)
    }
}