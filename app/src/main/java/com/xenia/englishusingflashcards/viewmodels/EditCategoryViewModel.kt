package com.xenia.englishusingflashcards.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.repositories.CategoryRepository
import com.xenia.englishusingflashcards.repositories.WordRepository
import com.xenia.englishusingflashcards.room.database.AppDatabase
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditCategoryViewModel(app: Application) : ViewModel() {
    var categoryName by mutableStateOf("")
        private set

    var categoryImage by mutableIntStateOf(R.drawable.image_1)
        private set

    private val _listWordInCategory = MutableLiveData<List<Word>>(emptyList())
    val listWordInCategory: LiveData<List<Word>>
        get() = _listWordInCategory

    private val appDb: AppDatabase = AppDatabase.getInstance(app)

    private val categoryRepository: CategoryRepository

    init {
        val categoryDao = appDb.categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
    }

    fun updateCategoryName(input: String) {
        categoryName = input
    }

    fun updateCategoryImage(input: Int) {
        categoryImage = input
    }

    fun updateListWordsInCategory(word: Word) {
        viewModelScope.launch (Dispatchers.IO) {
            val list = _listWordInCategory.value?.toMutableList()
            list?.add(word.copy())
            _listWordInCategory.postValue(list)
        }
    }

    fun saveCategoryWithWords(oldName: String) {
        viewModelScope.launch (Dispatchers.IO) {
            // _listWordInCategory.value?.let { wordRepository.insertListWords(it) }
            categoryRepository.updateCategoryNameAndImage(oldName, categoryName, categoryImage)
        }
    }

    fun getListWordsInCategory(categoryName: String) {
        _listWordInCategory.value = categoryRepository.getWordsInCategory(categoryName)
    }

}