package com.xenia.englishusingflashcards.room.entities

import androidx.room.Entity

@Entity
data class NotificationTable (
    var turnOff : Boolean = false,
    var time : String = "08:00",
)