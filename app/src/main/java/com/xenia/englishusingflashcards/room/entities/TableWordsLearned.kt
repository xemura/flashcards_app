package com.xenia.englishusingflashcards.room.entities

import androidx.room.Entity

@Entity
data class TableWordsLearned (
    var words : List<Word>?,
    var count : Int,
)