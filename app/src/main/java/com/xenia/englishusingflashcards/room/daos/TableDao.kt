package com.xenia.englishusingflashcards.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xenia.englishusingflashcards.room.entities.TableStudy
import com.xenia.englishusingflashcards.room.entities.TableWordsLearned
import com.xenia.englishusingflashcards.room.entities.TableWordsThatIKnow

@Dao
interface TableDao {
    @Query("SELECT * FROM tablestudy")
    fun getWordsInTableStudy(): TableStudy

    @Query("SELECT * FROM tablewordsthatiknow")
    fun getWordsInTableWordsThatIKnow(): TableWordsThatIKnow

    @Query("SELECT * FROM tablewordslearned")
    fun getWordsInTableLearned(): TableWordsLearned

    @Insert
    fun insertAll(vararg tableStudy: TableStudy)

    @Insert
    fun insertAll(vararg tableWordsThatIKnow: TableWordsThatIKnow)

    @Insert
    fun insertAll(vararg tableWordsLearned: TableWordsLearned)

    @Delete
    fun delete(category: TableStudy)
}