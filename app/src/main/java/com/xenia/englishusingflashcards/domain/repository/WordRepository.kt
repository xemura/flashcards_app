package com.xenia.englishusingflashcards.domain.repository

import com.xenia.englishusingflashcards.domain.models.WordModel

interface WordRepository {
    fun insertListWords(words: List<WordModel>)
    fun insertWord(word: WordModel)
}