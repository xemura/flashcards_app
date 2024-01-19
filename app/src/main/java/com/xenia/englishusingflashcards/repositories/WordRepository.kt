package com.xenia.englishusingflashcards.repositories

import com.xenia.englishusingflashcards.room.daos.WordDao
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordRepository(private val wordDao: WordDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertListWords(words: List<Word>) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.insertAll(words)
        }
    }

    fun insertWord(word: Word) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.insertWord(word)
        }
    }

    fun updateWordsInCategory(categoryNameNew: String, categoryNameOld: String) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.updateCategoryInWords(categoryNameNew, categoryNameOld)
        }
    }
}