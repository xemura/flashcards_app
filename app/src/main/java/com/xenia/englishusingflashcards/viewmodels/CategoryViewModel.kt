package com.xenia.englishusingflashcards.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.repositories.CategoryRepository
import com.xenia.englishusingflashcards.room.database.AppDatabase
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(app: Application) : ViewModel() {

    private val categoryRepository: CategoryRepository
    private var categories: List<Category>? = null
    private var wordsInCategory : List<Word>? = null

    init {
        val appDb = AppDatabase.getInstance(app)
        val categoryDao = appDb.categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
    }

    fun getCategories() : List<Category>? {
        viewModelScope.launch(Dispatchers.IO) {
            categories = categoryRepository.getCategories()
        }
        return categories
    }

    fun getWordsInCategory(categoryName : String) : List<Word>? {
        viewModelScope.launch(Dispatchers.IO) {
            wordsInCategory = categoryRepository.getWordsInCategory(categoryName)
        }
        return wordsInCategory
    }
}