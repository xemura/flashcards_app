package com.xenia.englishusingflashcards.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category (
    var level: String,
    @PrimaryKey var categoryName : String,
    var image : String,
    @ColumnInfo(name = "words_in_category") var words : List<Word>,
    var progress: Float
)