package com.xenia.englishusingflashcards.domain.usecases

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.repository.LearnRepository
import kotlinx.coroutines.flow.Flow

class GetWordsFromStudyTableUseCase(private val learnRepository: LearnRepository) {
    fun getWordsFromStudyTable(): Flow<List<WordsStudyModel>?> {
        return learnRepository.getWordsFromStudyTable()
    }
}