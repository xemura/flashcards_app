package com.xenia.englishusingflashcards.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.repositories.CategoryRepository
import com.xenia.englishusingflashcards.repositories.CreateCategoryRepository
import com.xenia.englishusingflashcards.repositories.WordRepository
import com.xenia.englishusingflashcards.room.database.AppDatabase
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateCategoryViewModel(app: Application) : ViewModel() {

    var categoryName by mutableStateOf("")
        private set

    private val _listWordInCategory = MutableStateFlow(emptyList<Word>())
    var listWordInCategory: Flow<List<Word>?> = _listWordInCategory
    private var list = _listWordInCategory.value.toMutableList()

    private val createCategoryRepository: CreateCategoryRepository

    private val categoryRepository: CategoryRepository
    private val wordRepository: WordRepository

    init {
        val appDb = AppDatabase.getInstance(app)
        val categoryDao = appDb.categoryDao()
        val wordDao = appDb.wordDao()
        categoryRepository = CategoryRepository(categoryDao)
        wordRepository = WordRepository(wordDao)
        createCategoryRepository = CreateCategoryRepository(wordDao)
    }

    fun updateCategoryName(input: String) {
        categoryName = input
    }

    fun updateListWordsInCategory(word: Word) {
        list.add(word)
        _listWordInCategory.value = list
        Log.d("CreateCategory", "update ${_listWordInCategory.value.toString()}")
    }

    fun saveCategoryWithWords() {
        wordRepository.insertListWords(_listWordInCategory.value)
        categoryRepository.createCategory(
            Category(
                categoryName = categoryName,
                image = "",
                progress = 0.0f
            )
        )
    }

    fun insertAll(list: List<Word>) {
        viewModelScope.launch (Dispatchers.IO) {
            createCategoryRepository.insertWordsInCategory(list)
            _listWordInCategory.value = list
        }
    }
}