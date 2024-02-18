package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.data.repository.CategoryRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.usecases.DeleteCategoryUseCase
import com.xenia.englishusingflashcards.domain.usecases.GetAllCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CategoryViewModel(app: Application) : ViewModel() {

    private val _categories: MutableStateFlow<List<CategoryModel>?> = MutableStateFlow(emptyList<CategoryModel>())
    val categories: StateFlow<List<CategoryModel>?> = _categories.asStateFlow()

    private val repository = CategoryRepositoryImpl(app)
    private val deleteCategoryUseCase = DeleteCategoryUseCase(repository)
    private val getAllCategoriesUseCase = GetAllCategoriesUseCase(repository)

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getAllCategoriesUseCase.getCategories()
                .flowOn(Dispatchers.IO)
                .catch {

                }
                .collect { categories ->
                    Log.d("CategoryViewModel", "collect")
                    _categories.value = categories
                }
        }
    }

    fun deleteCategory(category: CategoryModel) {
        viewModelScope.launch {
            deleteCategoryUseCase.deleteCategory(category = category)
        }
    }
}