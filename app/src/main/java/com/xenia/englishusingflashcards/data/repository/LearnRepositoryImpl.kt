package com.xenia.englishusingflashcards.data.repository

import android.app.Application
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
        coroutineScope.launch(Dispatchers.IO) {
            tableStudyDao.addWordInStudyTable(mapperWord.mapWordToStudy(word))
        }
    }

    override fun addWordsInStudyTable(listWords: List<WordModel>?) {
        coroutineScope.launch(Dispatchers.IO) {
            tableStudyDao.addAll(mapperWord.mapWordsToStudy(listWords))
        }
    }

    override fun deleteWordFromStudyTable(
        word: String,
        translate: String,
        sentence: String
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            tableStudyDao.deleteWordInStudyTable(word, translate, sentence)
        }
    }

    override fun getWordsToStudy(study: String): Flow<List<WordsStudyModel>?> =
        tableStudyDao.getWordsToStudy(study).map {
            mapperWord.mapWordsToDomain(it)
        }

    override fun getWordsToKnow(know: String): Flow<List<WordsStudyModel>?> =
        tableStudyDao.getWordsToKnow(know).map {
            mapperWord.mapWordsToDomain(it)
        }

    override fun getWordsToLearned(learned: String): Flow<List<WordsStudyModel>?> =
        tableStudyDao.getWordsToLearned(learned).map {
            mapperWord.mapWordsToDomain(it)
        }

    override fun updateStateWords() {
        coroutineScope.launch(Dispatchers.Default) {
            val words = tableStudyDao.getWordsFromStudyTable()
            words?.forEach { word ->
                if ((word.theNumberOfRepetitions >= 5) and (word.theNumberOfRepetitions < 7)) {
                    tableStudyDao.updateState(word.id, "выучено")
                }
                val date = word.dateOfTheNextRepetition.formatToDate()
                val nextDate = date.plusHours(word.theRepetitionInterval.toInt().toLong())

                if (nextDate.equals(LocalDateTime.now()) or nextDate.isBefore(LocalDateTime.now())) {
                    val currentDate = LocalDateTime.now()
                    val beginning = LocalDate.of(currentDate.year, currentDate.month, currentDate.dayOfMonth)
                    val dateTime = LocalDateTime.of(beginning, LocalTime.of(currentDate.hour, currentDate.minute))

                    tableStudyDao.updateDateOfTheNextRepetition(word.id, dateTime.toString(), "учить")

                    if (word.theNumberOfRepetitions >= 7) {
                        tableStudyDao.updateRepetitionIntervalAndNumberOfRepetition(
                            word.id,
                            "учить",
                            1,
                            "1"
                        )
                    }
                }
            }
        }
    }

    override fun guessedCardAndMoveToKnowState(
        wordId: Int
    ) {
        coroutineScope.launch(Dispatchers.Default) {
            val word = tableStudyDao.getWordFromStudyTable(wordId)
            val numberOfRepetition = word.theNumberOfRepetitions + 1
            val date = word.dateOfTheNextRepetition
            var dateOfTheNextRepetition = date.formatToDate()
            dateOfTheNextRepetition = dateOfTheNextRepetition.plusHours(
                word.theRepetitionInterval.toInt().toLong()
            )
            val state = "знаю"
            val interval = (numberOfRepetition - 1) * word.theRepetitionInterval.toInt()

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

private fun String.formatToDate(): LocalDateTime {
    val date = this
    val localDate = LocalDate.parse(date.substring(0, 10))

    val hours = date.substring(date.indexOf("T") + 1, date.indexOf(":")).toInt()
    val minutes = date.substring(date.indexOf(":") + 1).toInt()

    return LocalDateTime.of(localDate, LocalTime.of(hours, minutes))
}