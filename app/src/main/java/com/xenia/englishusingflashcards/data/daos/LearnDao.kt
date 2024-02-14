package com.xenia.englishusingflashcards.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xenia.englishusingflashcards.data.entities.TableStudyWord
import com.xenia.englishusingflashcards.data.entities.Word

@Dao
interface LearnDao {

    @Query("SELECT * FROM word WHERE in_process = 1")
    suspend fun getWordsToLearn(): List<Word>?

    @Delete
    suspend fun deleteWordInCategory(tableStudyWord: TableStudyWord)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWordInStudyTable(tableStudyWord: TableStudyWord)

}