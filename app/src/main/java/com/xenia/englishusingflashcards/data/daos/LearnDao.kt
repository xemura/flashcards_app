package com.xenia.englishusingflashcards.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.xenia.englishusingflashcards.data.entities.Word

@Dao
interface LearnDao {

    @Query("SELECT * FROM word WHERE in_process = 1")
    suspend fun getWordsToLearn(): List<Word>?

}