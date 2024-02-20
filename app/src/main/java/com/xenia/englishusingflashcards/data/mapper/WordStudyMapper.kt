package com.xenia.englishusingflashcards.data.mapper

import android.util.Log
import com.xenia.englishusingflashcards.data.entities.TableStudyWord
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date
import java.util.Locale

class WordStudyMapper {
    fun mapWordToStudy(word: WordModel): TableStudyWord {
        val currentDate = LocalDateTime.now()

        val beginning = LocalDate.of(currentDate.year, currentDate.month, currentDate.dayOfMonth)
        var dateTime = LocalDateTime.of(beginning, LocalTime.of(currentDate.hour, currentDate.minute))

        Log.d("WordStudyMapper", dateTime.toString())
        //dateTime = dateTime.plusHours(12)
        Log.d("WordStudyMapper", dateTime.toString())

        return TableStudyWord(
            id = word.id,
            word = word.word,
            translate = word.translate,
            sentence = word.sentence,
            state = "учить",
            theNumberOfRepetitions = 1,
            theRepetitionInterval = "1", // интервал в часах?
            dateOfTheNextRepetition = dateTime.toString()
        )
    }

    fun mapWordsToStudy(words: List<WordModel>?) : List<TableStudyWord> {
        val currentDate = LocalDateTime.now()

        val beginning = LocalDate.of(currentDate.year, currentDate.month, currentDate.dayOfMonth)
        val dateTime = LocalDateTime.of(beginning, LocalTime.of(currentDate.hour, currentDate.minute))

        // если сами настраиваем, то можно из WordStudyModel убрать лишние поля

        val list = mutableListOf<TableStudyWord>()
        if (words != null) {
            for (word in words) {
                val word = TableStudyWord(
                    id = word.id,
                    word = word.word,
                    translate = word.translate,
                    sentence = word.sentence,
                    state = "учить",
                    theNumberOfRepetitions = 1,
                    theRepetitionInterval = "1",
                    dateOfTheNextRepetition = dateTime.toString()
                )
                list.add(word)
            }
        }
        return list
    }

    fun mapWordsToDomain(words: List<TableStudyWord>?) : List<WordsStudyModel> {
        val list = mutableListOf<WordsStudyModel>()
        if (words != null) {
            for (word in words) {
                val word = WordsStudyModel(
                    id = word.id,
                    word = word.word,
                    translate = word.translate,
                    sentence = word.sentence,
                    theDateOfTheWordStudy = "",
                    theNumberOfRepetitions = 1,
                    theRepetitionInterval = 0.0,
                    theRepetitionIntervalAfterTheNRepetition = 0.0
                )
                list.add(word)
            }
        }
        return list
    }
}