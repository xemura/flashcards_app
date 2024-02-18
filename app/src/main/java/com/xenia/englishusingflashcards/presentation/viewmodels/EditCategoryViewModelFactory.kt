package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditCategoryViewModelFactory(
    private val app: Application,
    private val categoryName: String
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditCategoryViewModel(app, categoryName) as T
    }
}