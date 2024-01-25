package com.xenia.englishusingflashcards.data.repository

import android.app.Application
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.domain.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordRepositoryImpl(app: Application): WordRepository {

    val appDb = AppDatabase.getInstance(app)
    val wordDao = appDb.wordDao()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun insertListWords(words: List<Word>) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.insertAll(words)
        }
    }

    override fun insertWord(word: Word) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.insertWord(word)
        }
    }

    override fun updateWordsInCategory(categoryNameNew: String, categoryNameOld: String) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.updateCategoryInWords(categoryNameNew, categoryNameOld)
        }
    }
}