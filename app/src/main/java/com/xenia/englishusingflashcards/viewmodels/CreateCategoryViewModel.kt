package com.xenia.englishusingflashcards.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateCategoryViewModel() : ViewModel() {

    var categoryName by mutableStateOf("")
        private set

    fun updateCategoryName(input: String) {
        categoryName = input
    }
}