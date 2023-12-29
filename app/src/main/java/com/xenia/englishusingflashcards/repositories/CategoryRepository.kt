package com.xenia.englishusingflashcards.repositories

import com.xenia.englishusingflashcards.room.daos.CategoryDao
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Level
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRepository(private val categoryDao: CategoryDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var categoriesInLevel : Level? = null
    private var wordsInCategory : Category? = null

    fun getCategoriesByLevel(levelName: String) : Level? {
        coroutineScope.launch(Dispatchers.IO) {
            categoriesInLevel = categoryDao.getCategoriesByLevel(levelName)
        }
        return categoriesInLevel
    }

    fun getWordsInCategory(levelName : String, categoryName : String) : Category? {
        coroutineScope.launch(Dispatchers.IO) {
            wordsInCategory = categoryDao.getWordsInCategory(levelName, categoryName)
        }
        return wordsInCategory
    }
}