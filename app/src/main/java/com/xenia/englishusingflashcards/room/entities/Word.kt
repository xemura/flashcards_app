package com.xenia.englishusingflashcards.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word (
    @PrimaryKey(autoGenerate = true)
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
