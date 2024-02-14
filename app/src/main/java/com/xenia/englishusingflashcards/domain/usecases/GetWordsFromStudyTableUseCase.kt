package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.repository.LearnRepository

class GetWordsFromStudyTableUseCase(private val learnRepository: LearnRepository) {
    fun getWordsFromStudyTable(): List<WordsStudyModel>? {
        return learnRepository.getWordsFromStudyTable()
    }
}