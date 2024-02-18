package com.xenia.englishusingflashcards.data.mapper

import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel

class WordMapper {
    fun mapWord(words: List<Word>?) : List<WordModel> {
        val list = mutableListOf<WordModel>()
        if (words != null) {
            for (word in words) {
                val wordModel = WordModel(
                    id = word.id,
                    categoryName = word.categoryName,
                    word = word.word,
                    translate = word.translate,
                    sentence = word.sentence,
                    inProcess = word.inProcess,
                    theDateOfTheWordStudy = word.theDateOfTheWordStudy,
                    theNumberOfRepetitions = word.theNumberOfRepetitions,
                    theRepetitionInterval = word.theRepetitionInterval,
                    theRepetitionIntervalAfterTheNRepetition = word.theRepetitionIntervalAfterTheNRepetition
                )
                list.add(wordModel)
            }
        }
        return list
    }

    fun mapWordToData(words: List<WordModel>?) : List<Word> {
        val list = mutableListOf<Word>()
        if (words != null) {
            for (word in words) {
                val word = Word(
                    id = word.id,
                    categoryName = word.categoryName,
                    word = word.word,
                    translate = word.translate,
                    sentence = word.sentence,
                    inProcess = word.inProcess,
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

    fun mapWord(word: WordModel) = Word(
            id = word.id,
            categoryName = word.categoryName,
            word = word.word,
            translate = word.translate,
            sentence = word.sentence,
            inProcess = word.inProcess,
            theDateOfTheWordStudy = word.theDateOfTheWordStudy,
            theNumberOfRepetitions = word.theNumberOfRepetitions,
            theRepetitionInterval = word.theRepetitionInterval,
            theRepetitionIntervalAfterTheNRepetition = word.theRepetitionIntervalAfterTheNRepetition
    )
}