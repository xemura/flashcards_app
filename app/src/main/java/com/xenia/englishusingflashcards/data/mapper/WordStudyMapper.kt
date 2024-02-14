package com.xenia.englishusingflashcards.data.mapper

import com.xenia.englishusingflashcards.data.entities.TableStudyWord
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel

class WordStudyMapper {
    fun mapWordToStudy(word: WordModel) = TableStudyWord(
        id = word.id,
        word = word.word,
        translate = word.translate,
        sentence = word.sentence,
        theDateOfTheWordStudy = word.theDateOfTheWordStudy,
        theNumberOfRepetitions = word.theNumberOfRepetitions,
        theRepetitionInterval = word.theRepetitionInterval,
        theRepetitionIntervalAfterTheNRepetition = word.theRepetitionIntervalAfterTheNRepetition
    )

    fun mapWordsToStudy(words: List<WordModel>?) : List<TableStudyWord> {
        val list = mutableListOf<TableStudyWord>()
        if (words != null) {
            for (word in words) {
                val word = TableStudyWord(
                    id = word.id,
                    word = word.word,
                    translate = word.translate,
                    sentence = word.sentence,
                    theDateOfTheWordStudy = word.theDateOfTheWordStudy,
                    theNumberOfRepetitions = word.theNumberOfRepetitions,
                    theRepetitionInterval = word.theRepetitionInterval,
                    theRepetitionIntervalAfterTheNRepetition = word.theRepetitionIntervalAfterTheNRepetition
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
                    theDateOfTheWordStudy = word.theDateOfTheWordStudy,
                    theNumberOfRepetitions = word.theNumberOfRepetitions,
                    theRepetitionInterval = word.theRepetitionInterval,
                    theRepetitionIntervalAfterTheNRepetition = word.theRepetitionIntervalAfterTheNRepetition
                )
                list.add(word)
            }
        }
        return list
    }
}