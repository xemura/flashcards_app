package com.xenia.englishusingflashcards.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "category_name") val categoryName: String,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "translate") val translate: String,
    @ColumnInfo(name = "sentence") val sentence: String
)
