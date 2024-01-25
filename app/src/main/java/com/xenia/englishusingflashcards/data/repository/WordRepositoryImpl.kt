package com.xenia.englishusingflashcards.data.repository

import android.app.Application
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.data.mapper.WordMapper
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordRepositoryImpl(app: Application): WordRepository {

    private val appDb = AppDatabase.getInstance(app)
    private val wordDao = appDb.wordDao()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val mapperWord = WordMapper()

    override fun insertListWords(words: List<WordModel>) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.insertAll(mapperWord.mapWordToData(words))
        }
    }

    override fun insertWord(word: WordModel) {
        coroutineScope.launch(Dispatchers.IO) {
            wordDao.insertWord(mapperWord.mapWord(word))
        }
    }
}