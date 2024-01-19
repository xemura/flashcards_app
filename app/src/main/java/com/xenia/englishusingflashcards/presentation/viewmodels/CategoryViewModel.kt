package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.xenia.englishusingflashcards.repositories.CategoryRepository
import com.xenia.englishusingflashcards.room.database.AppDatabase
import com.xenia.englishusingflashcards.room.entities.Category
import kotlinx.coroutines.flow.Flow

class CategoryViewModel(app: Application) : ViewModel() {

    private val appDb: AppDatabase = AppDatabase.getInstance(app)
    private val categoryRepository: CategoryRepository
    internal var categories: Flow<List<Category>?> = appDb.categoryDao().getCategories()

    init {
        val categoryDao = appDb.categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
    }

    fun deleteCategory(category: Category) {
        categoryRepository.deleteCategoryWithWords(category)
    }
}