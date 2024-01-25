package com.xenia.englishusingflashcards.data.repository

import android.app.Application
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRepositoryImpl(app: Application) : CategoryRepository {

    private val appDb = AppDatabase.getInstance(app)
    private val categoryDao = appDb.categoryDao()
    private val wordDao = appDb.wordDao()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var wordsInCategory: List<Word>? = null


    override fun updateCategoryName(oldName: String, newName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategoryName(oldName, newName)
            wordDao.updateCategoryInWords(newName, oldName)
        }
    }

    override fun updateCategoryImage(oldImage: Int, newImage: Int, categoryName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategoryImage(oldImage, newImage, categoryName)
        }
    }

    override fun getWordsFromCategory(categoryName: String): List<Word>? {
        coroutineScope.launch(Dispatchers.IO) {
            wordsInCategory = categoryDao.getWordsInCategory(categoryName)
        }
        return wordsInCategory
    }

    override fun deleteWordFromCategory(
        categoryName: String,
        word: String,
        translate: String,
        sentence: String
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.deleteWordInCategory(categoryName, word, translate, sentence)
        }
    }

    override fun createCategory(category: Category, listWordsInCategory: List<Word>?) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.createCategory(category)
            if (!listWordsInCategory.isNullOrEmpty()) {
                wordDao.insertAll(listWordsInCategory) //
            }
        }
    }

    override fun deleteCategory(category: Category) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.deleteCategory(category)
            categoryDao.deleteWordsFromCategory(category.categoryName)
        }
    }

    override fun setUpNotification() {
        TODO("Not yet implemented")
    }
}