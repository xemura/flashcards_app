package com.xenia.englishusingflashcards.data.repository

import android.app.Application
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.entities.TableStudyWord
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.data.mapper.WordMapper
import com.xenia.englishusingflashcards.data.mapper.WordStudyMapper
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.repository.LearnRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LearnRepositoryImpl(app: Application): LearnRepository {

    private val appDb = AppDatabase.getInstance(app)

    private val tableStudyDao = appDb.tableStudyDao()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var wordsToLearn: List<TableStudyWord>? = null

    private val mapperWord = WordStudyMapper()

    override fun addWordInStudyTable(word: WordModel) {
        coroutineScope.launch {
            tableStudyDao.addWordInStudyTable(mapperWord.mapWordToStudy(word))
        }
    }

    override fun addWordsInStudyTable(listWords: List<WordModel>?) {
        coroutineScope.launch {
            tableStudyDao.addAll(mapperWord.mapWordsToStudy(listWords))
        }
    }

    override fun deleteWordFromStudyTable(
        word: String,
        translate: String,
        sentence: String
    ) {
        coroutineScope.launch {
            tableStudyDao.deleteWordInStudyTable(word, translate, sentence)
        }
    }

    override fun getWordsFromStudyTable(): Flow<List<WordsStudyModel>?> =
        tableStudyDao.getWordsInCategory().map {
            mapperWord.mapWordsToDomain(it)
        }
}