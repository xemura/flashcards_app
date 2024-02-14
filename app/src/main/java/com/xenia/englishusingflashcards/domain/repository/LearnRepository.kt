package com.xenia.englishusingflashcards.domain.repository

import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel

interface LearnRepository {
    fun addWordInStudyTable(word: WordModel)
    fun addWordsInStudyTable(listWords: List<WordModel>?)
    fun deleteWordFromStudyTable(word: String, translate: String, sentence: String)
    fun getWordsFromStudyTable(): List<WordsStudyModel>?
}