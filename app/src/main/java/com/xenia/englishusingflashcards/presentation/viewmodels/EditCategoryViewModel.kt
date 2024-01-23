package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.repositories.CategoryRepository
import com.xenia.englishusingflashcards.repositories.WordRepository
import com.xenia.englishusingflashcards.room.database.AppDatabase
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditCategoryViewModel(app: Application) : ViewModel() {
    private val _categoryName = MutableLiveData<String>()
    val categoryName : LiveData<String> = _categoryName

    fun updateCategoryName(input: String) {
        _categoryName.value = input
    }

    private val _categoryImage = MutableLiveData<Int>()
    val categoryImage : LiveData<Int> = _categoryImage

    fun updateCategoryImage(input: Int) {
        _categoryImage.value = input
    }

    private val _listWordInCategory = MutableLiveData<List<Word>?>(emptyList())
    val listWordInCategory: LiveData<List<Word>?>
        get() = _listWordInCategory


    private val appDb: AppDatabase = AppDatabase.getInstance(app)

    private val categoryRepository: CategoryRepository
    private val wordRepository: WordRepository

    init {
        val categoryDao = appDb.categoryDao()
        val wordDao = appDb.wordDao()
        categoryRepository = CategoryRepository(categoryDao)
        wordRepository = WordRepository(wordDao)
    }

    fun updateListWordsInCategory(word: Word) {
        viewModelScope.launch (Dispatchers.IO) {
            val list = _listWordInCategory.value?.toMutableList()
            list?.add(word.copy())
            _listWordInCategory.postValue(list)

            wordRepository.insertWord(word)

        }
    }

    fun updateImageCategory(oldImage: Int, newImage: Int, categoryName: String) {
        categoryRepository.updateCategoryImage(oldImage, newImage, categoryName)
    }

    fun updateCategoryName(oldName: String, newName: String) {
        categoryRepository.updateCategoryName(oldName, newName)
        wordRepository.updateWordsInCategory(newName, oldName)
    }

    fun deleteWordInCategory(categoryName: String, word: String, translate: String, sentence: String) {
        viewModelScope.launch (Dispatchers.IO) {
            categoryRepository.deleteWordInCategory(categoryName, word, translate, sentence)
            val list = _listWordInCategory.value?.toMutableList()
            list?.removeIf { ((it.word == word) and (it.translate == translate) and (it.sentence == sentence)) }
            Log.d("Tag", "list = $list")
            _listWordInCategory.postValue(list)
        }
    }

    fun getListWordsInCategory(categoryName: String) {
        _listWordInCategory.value = categoryRepository.getWordsInCategory(categoryName)
    }
}