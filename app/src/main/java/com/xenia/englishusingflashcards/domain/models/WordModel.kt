package com.xenia.englishusingflashcards.domain.models

data class WordModel(
    val id: Int = 0,
    val categoryName: String,
    val word : String,
    val translate : String,
    val sentence : String,
    val inProcess: Boolean,
    val theDateOfTheWordStudy: String,
    val theNumberOfRepetitions: Int,
    val theRepetitionInterval: Double,
    val theRepetitionIntervalAfterTheNRepetition: Double,
)