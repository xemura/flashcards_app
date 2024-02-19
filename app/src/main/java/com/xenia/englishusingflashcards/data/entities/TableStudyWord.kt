package com.xenia.englishusingflashcards.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_study")
data class TableStudyWord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "translate") val translate: String,
    @ColumnInfo(name = "sentence") val sentence: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "the_number_of_repetitions") val theNumberOfRepetitions: Int,
    @ColumnInfo(name = "the_repetition_interval") val theRepetitionInterval: String,
    @ColumnInfo(name = "date_of_the_next_repetition") val dateOfTheNextRepetition: String,
)
