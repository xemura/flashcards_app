package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.xenia.englishusingflashcards.data.repository.CategoryRepositoryImpl
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.usecases.DeleteCategoryUseCase
import kotlinx.coroutines.flow.Flow

class CategoryViewModel(app: Application) : ViewModel() {

    private val appDb: AppDatabase = AppDatabase.getInstance(app)
    internal var categories: Flow<List<Category>?> = appDb.categoryDao().getCategories()

    private val repository = CategoryRepositoryImpl(app)
    private val deleteCategoryUseCase = DeleteCategoryUseCase(repository)

    fun deleteCategory(category: CategoryModel) {
        deleteCategoryUseCase.deleteCategory(category = category)
    }
}