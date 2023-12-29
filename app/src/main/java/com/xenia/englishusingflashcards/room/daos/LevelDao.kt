package com.xenia.englishusingflashcards.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xenia.englishusingflashcards.room.entities.Level

@Dao
interface LevelDao {
    @Query("SELECT * FROM level")
    fun getAll(): List<Level>

    @Insert
    fun insertAll(vararg levels: Level)

    @Delete
    fun delete(level: Level)
}