package com.xenia.englishusingflashcards.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM word WHERE category_name = :categoryName")
    suspend fun getWordsInCategory(categoryName: String): List<Word>?

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<Category>>

    @Insert
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(categories: Category)

    @Query("DELETE FROM word WHERE category_name =:categoryName")
    suspend fun deleteWordsFromCategory(categoryName: String)

    @Query("SELECT * FROM category WHERE category_name = :categoryName LIMIT 1")
    suspend fun getCategoryByName(categoryName: String) : Category

    @Query("UPDATE category SET category_name =:newName, image =:newImage WHERE category_name =:oldName")
    suspend fun updateCategoryNameAndImage(oldName: String,
                                           newName: String,
                                           newImage: Int)

}