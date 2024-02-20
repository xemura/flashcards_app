package com.xenia.englishusingflashcards.data.repository

import android.app.Application
import android.util.Log
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.mapper.WordStudyMapper
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.repository.LearnRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class LearnRepositoryImpl(app: Application): LearnRepository {

    private val appDb = AppDatabase.getInstance(app)

    private val tableStudyDao = appDb.tableStudyDao()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

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
        tableStudyDao.getWordsFromStudyTable().map {
            mapperWord.mapWordsToDomain(it)
        }

    override fun guessedCardAndMoveToKnowState(
        wordId: Int
    ) {
        coroutineScope.launch {
            val word = tableStudyDao.getWordFromStudyTable(wordId)

            Log.d("guessedCardAndMoveToKnowState", word.toString())

            val numberOfRepetition = word.theNumberOfRepetitions + 1
            val date = word.dateOfTheNextRepetition
            var dateOfTheNextRepetition = date.formatToDate()
            dateOfTheNextRepetition = dateOfTheNextRepetition.plusHours(
                word.theRepetitionInterval.toInt().toLong()
            )

            val state = "знаю"
            val interval = (numberOfRepetition - 1) * word.theRepetitionInterval.toInt()

            Log.d("guessedCardAndMoveToKnowState", numberOfRepetition.toString())
            Log.d("guessedCardAndMoveToKnowState", dateOfTheNextRepetition.toString())
            Log.d("guessedCardAndMoveToKnowState", state)
            Log.d("guessedCardAndMoveToKnowState", interval.toString())
            Log.d("guessedCardAndMoveToKnowState", wordId.toString())

            tableStudyDao.guessedCardAndMoveToKnowState(
                wordId,
                dateOfTheNextRepetition.toString(),
                numberOfRepetition,
                state,
                interval.toString()
            )
        }
    }
}

private fun String.formatToDate() : LocalDateTime {
    val date = this
    Log.d("formatToDate", date)
    val localDate = LocalDate.parse(date.substring(0, 10))
    Log.d("formatToDate", localDate.toString())

    val hours = date.substring(date.indexOf("T") + 1, date.indexOf(":")).toInt()
    Log.d("formatToDate", hours.toString())
    val minutes = date.substring(date.indexOf(":") + 1).toInt()
    Log.d("formatToDate", minutes.toString())

    val dateTime = LocalDateTime.of(localDate, LocalTime.of(hours, minutes))
    Log.d("formatToDate", dateTime.toString())

    return dateTime
}