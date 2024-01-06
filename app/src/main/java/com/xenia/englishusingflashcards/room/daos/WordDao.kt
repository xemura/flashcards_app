package com.xenia.englishusingflashcards.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xenia.englishusingflashcards.room.entities.Word

@Dao
interface WordDao {
    @Query("SELECT word FROM word")
    fun getAll(): Word

    @Insert
    fun insertAll(vararg word: Word)

    @Delete
    fun delete(word: Word)
}