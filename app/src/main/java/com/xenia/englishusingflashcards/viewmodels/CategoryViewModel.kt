package com.xenia.englishusingflashcards.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.repositories.CategoryRepository
import com.xenia.englishusingflashcards.room.database.AppDatabase
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryViewModel(app: Application) : ViewModel() {

    private val appDb: AppDatabase = AppDatabase.getInstance(app)

    private val categoryRepository: CategoryRepository
    internal var categories: Flow<List<Category>?> = appDb.categoryDao().getCategories()
    private var wordsInCategory : List<Word>? = null

    init {
        val categoryDao = appDb.categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
    }

    fun deleteCategory(category: Category) {
        categoryRepository.deleteCategoryWithWords(category)
    }

    fun getWordsInCategory(categoryName : String) : List<Word>? {
        viewModelScope.launch(Dispatchers.IO) {
            wordsInCategory = categoryRepository.getWordsInCategory(categoryName)
        }
        return wordsInCategory
    }
}