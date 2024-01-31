package com.xenia.englishusingflashcards.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_table")
data class NotificationTable(
    @PrimaryKey val turnOff : Boolean = false,
    val time : String = "08:00",
)