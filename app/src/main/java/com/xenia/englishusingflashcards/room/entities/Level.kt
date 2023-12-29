package com.xenia.englishusingflashcards.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Level (
    @PrimaryKey var level : String,
    @ColumnInfo(name = "level_list_categories") var categories : List<Category>?,
)