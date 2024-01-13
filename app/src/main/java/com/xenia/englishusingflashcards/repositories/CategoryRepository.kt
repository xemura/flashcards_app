package com.xenia.englishusingflashcards.repositories

import android.util.Log
import com.xenia.englishusingflashcards.room.daos.CategoryDao
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryRepository(private val categoryDao: CategoryDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var wordsInCategory : List<Word>? = null
    private var categoryByName : Category? = null

    fun insertCategory(category: Category) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.insertCategory(category)
        }
    }

    fun deleteCategoryWithWords(category: Category) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.deleteCategory(category)
            categoryDao.deleteWordsFromCategory(category.categoryName)
        }
    }

    fun getWordsInCategory(categoryName : String) : List<Word>? {
        coroutineScope.launch(Dispatchers.IO) {
            wordsInCategory = categoryDao.getWordsInCategory(categoryName)
        }
        return wordsInCategory
    }

//    suspend fun getCategoryByName(categoryName: String) : Category {
//        return withContext(Dispatchers.IO) {
//            Log.d("Tag", "repository")
//            categoryDao.getCategoryByName(categoryName)
//        }
//    }

    fun updateCategoryNameAndImage(oldName: String, newName: String, newImage: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategoryNameAndImage(oldName, newName, newImage)
        }
    }

}