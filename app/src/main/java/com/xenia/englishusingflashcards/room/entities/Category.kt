package com.xenia.englishusingflashcards.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "category_name") val categoryName : String,
    @ColumnInfo(name = "image") val image : Int
)