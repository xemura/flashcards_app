package com.xenia.englishusingflashcards.room.entities

import androidx.room.Entity

@Entity
data class Word (
    var word : String,
    var translate : String,
    var sentence : String,
    var inProcess: Boolean,
    var theDateOfTheWordStudy: String,
    var theNumberOfRepetitions: Int,
    var theRepetitionInterval: Double,
    var theRepetitionIntervalAfterTheNRepetition: Double,
)
