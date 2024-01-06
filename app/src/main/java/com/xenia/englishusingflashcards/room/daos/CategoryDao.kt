package com.xenia.englishusingflashcards.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word

@Dao
interface CategoryDao {
    @Query("SELECT * FROM word WHERE categoryName = :categoryName")
    fun getWordsInCategory(categoryName: String): List<Word>?

    @Query("SELECT * FROM category")
    fun getCategories(): List<Category>?

    @Insert
    fun insertCategory(vararg category: Category)

    @Delete
    fun deleteCategory(vararg categories: Category)
}