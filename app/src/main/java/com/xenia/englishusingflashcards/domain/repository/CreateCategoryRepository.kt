package com.xenia.englishusingflashcards.domain.repository

import com.xenia.englishusingflashcards.data.daos.WordDao
import com.xenia.englishusingflashcards.data.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateCategoryRepository(private val wordDao: WordDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var wordsInCategory : List<Word>? = null


    fun insertWordsInCategory(words : List<Word>) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.insertAll(words)
        }
    }
}