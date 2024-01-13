package com.xenia.englishusingflashcards.repositories

import com.xenia.englishusingflashcards.room.daos.WordDao
import com.xenia.englishusingflashcards.room.entities.Word
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

//    fun insertWordInCategory(word : Word) {
//        coroutineScope.launch(Dispatchers.IO) {
//            wordDao.insert(word)
//        }
//    }
}