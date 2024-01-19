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

    @Query("UPDATE category SET category_name =:newName WHERE category_name =:oldName")
    suspend fun updateCategoryName(oldName: String, newName: String)

    @Query("UPDATE category SET image =:newImage WHERE image =:oldImage AND category_name =:categoryName")
    suspend fun updateCategoryImage(oldImage: Int, newImage: Int, categoryName: String)

    @Query("DELETE FROM word WHERE " +
            "category_name = :categoryName AND " +
            "word = :word AND " +
            "translate=:translate AND " +
            "sentence=:sentence")
    suspend fun deleteWordInCategory(categoryName: String, word: String, translate: String, sentence: String)


    @Query("SELECT * FROM category WHERE category_name = :categoryName LIMIT 1")
    fun getCategoryByName(categoryName: String): Flow<Category?>

}