package com.xenia.englishusingflashcards.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "category_name") val categoryName: String,
    @ColumnInfo(name = "word") val word : String,
    @ColumnInfo(name = "translate") val translate : String,
    @ColumnInfo(name = "sentence") val sentence : String,
    @ColumnInfo(name = "in_process") val inProcess: Boolean,
    @ColumnInfo(name = "the_date_of_the_word_study") val theDateOfTheWordStudy: String,
    @ColumnInfo(name = "the_number_of_repetitions") val theNumberOfRepetitions: Int,
    @ColumnInfo(name = "the_repetition_interval") val theRepetitionInterval: Double,
    @ColumnInfo(name = "the_repetition_interval_after_the_n_repetition") val theRepetitionIntervalAfterTheNRepetition: Double,
)
