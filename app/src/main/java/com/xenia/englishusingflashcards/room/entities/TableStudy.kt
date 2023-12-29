package com.xenia.englishusingflashcards.room.entities

import androidx.room.Entity

@Entity
data class TableStudy (
    var words : List<Word>?,
    var count : Int,
)