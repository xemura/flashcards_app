package com.xenia.englishusingflashcards.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xenia.englishusingflashcards.data.entities.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<Word>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Delete
    fun delete(word: Word)

    @Query("UPDATE word SET category_name =:categoryNameNew WHERE category_name =:categoryNameOld")
    suspend fun updateCategoryInWords(categoryNameNew: String, categoryNameOld: String)
}