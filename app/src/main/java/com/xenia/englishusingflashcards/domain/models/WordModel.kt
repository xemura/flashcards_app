package com.xenia.englishusingflashcards.domain.models

data class WordModel(
    val id: Int = 0,
    val categoryName: String = "",
    val word : String = "",
    val translate : String = "",
    val sentence : String = "",
    val inProcess: Boolean = false,
    val theDateOfTheWordStudy: String = "",
    val theNumberOfRepetitions: Int = 0,
    val theRepetitionInterval: Double = 0.0,
    val theRepetitionIntervalAfterTheNRepetition: Double = 0.0,
)