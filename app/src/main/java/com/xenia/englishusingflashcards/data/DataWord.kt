package com.xenia.englishusingflashcards.data

data class WordInfo (
    var word : String,
    var translate : String,
    var sentence : String,
    var wordDetails : WordDetails
)

data class WordDetails(
    var theDateOfTheWordStudy: String,
    var theNumberOfRepetitions: Int,
    var theRepetitionInterval: Double,
    var theRepetitionIntervalAfterTheNRepetition: Double,
)