package com.xenia.englishusingflashcards.domain.usecases.main_screen

import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.repository.LearnRepository
import kotlinx.coroutines.flow.Flow

class GetWordsFromTableKnowUseCase(private val learnRepository: LearnRepository) {
    fun getWordsFromKnowTable(know: String): Flow<List<WordsStudyModel>?> {
        return learnRepository.getWordsToKnow(know)
    }
}