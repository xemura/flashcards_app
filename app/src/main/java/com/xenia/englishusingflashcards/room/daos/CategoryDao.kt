package com.xenia.englishusingflashcards.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM word WHERE category_name = :categoryName")
    fun getWordsInCategory(categoryName: String): List<Word>?

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<Category>>

    @Insert
    fun insertCategory(category: Category)

    @Delete
    fun deleteCategory(categories: Category)
}