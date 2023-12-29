package com.xenia.englishusingflashcards.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Level

@Dao
interface CategoryDao {

    @Query("SELECT * FROM level WHERE level = :levelName")
    fun getCategoriesByLevel(levelName : String): Level

    @Query("SELECT * FROM category WHERE level = :levelName AND categoryName = :categoryName")
    fun getWordsInCategory(levelName : String, categoryName : String): Category

    @Insert
    fun insertAll(vararg categories: Category)

    @Delete
    fun delete(category: Category)
}