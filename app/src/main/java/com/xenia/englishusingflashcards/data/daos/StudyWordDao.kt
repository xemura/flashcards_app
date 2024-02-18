package com.xenia.englishusingflashcards.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xenia.englishusingflashcards.data.entities.TableStudyWord
import com.xenia.englishusingflashcards.data.entities.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyWordDao {

    @Query("DELETE FROM table_study WHERE " +
            "word = :word AND " +
            "translate=:translate AND " +
            "sentence=:sentence")
    suspend fun deleteWordInStudyTable(word: String, translate: String, sentence: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWordInStudyTable(tableStudyWord: TableStudyWord)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAll(list: List<TableStudyWord>)

    @Query("SELECT * FROM table_study")
    fun getWordsInCategory(): Flow<List<TableStudyWord>?>
}