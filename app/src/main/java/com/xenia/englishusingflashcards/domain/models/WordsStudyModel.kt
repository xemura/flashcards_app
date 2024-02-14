package com.xenia.englishusingflashcards.domain.models


data class WordsStudyModel (
    val id: Int = 0,
    val word : String = "",
    val translate : String = "",
    val sentence : String = "",
    val theDateOfTheWordStudy: String = "",
    val theNumberOfRepetitions: Int = 0,
    val theRepetitionInterval: Double = 0.0,
    val theRepetitionIntervalAfterTheNRepetition: Double = 0.0,
)