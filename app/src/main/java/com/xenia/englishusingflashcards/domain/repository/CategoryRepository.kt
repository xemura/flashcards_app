package com.xenia.englishusingflashcards.domain.repository

import android.util.Log
import com.xenia.englishusingflashcards.data.daos.CategoryDao
import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.data.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryRepository(private val categoryDao: CategoryDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var wordsInCategory : List<Word>? = null

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

    fun deleteWordInCategory(categoryName: String, word: String, translate: String, sentence: String) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.deleteWordInCategory(categoryName, word, translate, sentence)
        }
    }

    fun updateCategoryName(oldName: String, newName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategoryName(oldName, newName)
        }
    }

    fun updateCategoryImage(oldImage: Int, newImage: Int, categoryName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategoryImage(oldImage, newImage, categoryName)
        }
    }

}