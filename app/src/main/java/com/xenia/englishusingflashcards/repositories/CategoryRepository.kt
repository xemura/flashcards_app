package com.xenia.englishusingflashcards.repositories

import com.xenia.englishusingflashcards.room.daos.CategoryDao
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRepository(private val categoryDao: CategoryDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var categories : List<Category>? = null
    private var wordsInCategory : List<Word>? = null

    fun getCategories() : List<Category>? {
        coroutineScope.launch(Dispatchers.IO) {
            categories = categoryDao.getCategories()
        }
        return categories
    }

    fun getWordsInCategory(categoryName : String) : List<Word>? {
        coroutineScope.launch(Dispatchers.IO) {
            wordsInCategory = categoryDao.getWordsInCategory(categoryName)
        }
        return wordsInCategory
    }
}