package com.xenia.englishusingflashcards.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xenia.englishusingflashcards.data.entities.Word

//@Dao
//interface TableDao {
//    @Query("SELECT words FROM table_study")
//    fun getWordsInTableStudy(): List<Word>
//
//    @Query("SELECT words FROM table_words_that_i_know")
//    fun getWordsInTableWordsThatIKnow(): List<Word>
//
//    @Query("SELECT words FROM table_words_learned")
//    fun getWordsInTableLearned(): List<Word>
//
//    @Insert
//    fun insertAll(vararg tableStudy: TableStudy)
//
//    @Insert
//    fun insertAll(vararg tableWordsThatIKnow: TableWordsThatIKnow)
//
//    @Insert
//    fun insertAll(vararg tableWordsLearned: TableWordsLearned)
//
////    @Delete
////    fun delete(word: Word)
//
//}