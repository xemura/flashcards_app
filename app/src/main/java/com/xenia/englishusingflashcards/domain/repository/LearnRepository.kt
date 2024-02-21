package com.xenia.englishusingflashcards.domain.repository

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import kotlinx.coroutines.flow.Flow

interface LearnRepository {
    fun addWordInStudyTable(word: WordModel)
    fun addWordsInStudyTable(listWords: List<WordModel>?)
    fun deleteWordFromStudyTable(word: String, translate: String, sentence: String)
    fun guessedCardAndMoveToKnowState(wordId: Int)
    fun getWordsToStudy(study: String): Flow<List<WordsStudyModel>?>
    fun getWordsToKnow(know: String): Flow<List<WordsStudyModel>?>
    fun getWordsToLearned(learned: String): Flow<List<WordsStudyModel>?>
    fun updateStateWords()
}