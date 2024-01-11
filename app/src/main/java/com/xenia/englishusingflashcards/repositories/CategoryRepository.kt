package com.xenia.englishusingflashcards.repositories

import com.xenia.englishusingflashcards.room.daos.CategoryDao
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRepository(private val categoryDao: CategoryDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var wordsInCategory : List<Word>? = null

    fun createCategory(category: Category) {
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
}