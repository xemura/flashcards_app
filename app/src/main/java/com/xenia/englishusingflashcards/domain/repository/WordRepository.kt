package com.xenia.englishusingflashcards.domain.repository

import com.xenia.englishusingflashcards.data.entities.Word

interface WordRepository {
    fun insertListWords(words: List<Word>)
    fun insertWord(word: Word)
    fun updateWordsInCategory(categoryNameNew: String, categoryNameOld: String)
}