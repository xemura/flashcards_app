package com.xenia.englishusingflashcards.presentation.viewmodels

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
import com.xenia.englishusingflashcards.data.repository.CategoryRepositoryImpl
import com.xenia.englishusingflashcards.data.repository.LearnRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.usecases.AddWordsInStudyTableUseCase
import com.xenia.englishusingflashcards.domain.usecases.CreateCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateCategoryViewModel(app: Application) : ViewModel() {

    var categoryName by mutableStateOf("")
        private set

    var categoryImage by mutableIntStateOf(R.drawable.image_1)
        private set

    private val _listWordInCategory = MutableLiveData<List<WordModel>?>(emptyList())
    val listWordInCategory: LiveData<List<WordModel>?>
        get() = _listWordInCategory


    private val repository = CategoryRepositoryImpl(app)
    private val repositoryLearn = LearnRepositoryImpl(app)

    private val createCategoryUseCase = CreateCategoryUseCase(repository)
    private val addWordsInStudyTableUseCase = AddWordsInStudyTableUseCase(repositoryLearn)

    fun updateCategoryName(input: String) {
        categoryName = input
    }

    fun updateCategoryImage(input: Int) {
        categoryImage = input
    }

    fun updateListWordsInCategory(word: WordModel) {
        viewModelScope.launch (Dispatchers.IO) {
            val list = _listWordInCategory.value?.toMutableList()
            list?.add(word.copy())
            _listWordInCategory.postValue(list)
        }
    }

    fun deleteWordInCategory(word: String, translate: String, sentence: String) {
        viewModelScope.launch (Dispatchers.IO) {
            val list = _listWordInCategory.value?.toMutableList()
            list?.removeIf { ((it.word == word) and (it.translate == translate) and (it.sentence == sentence)) }
            Log.d("Tag", "list = $list")
            _listWordInCategory.postValue(list)
        }
    }

    fun saveCategoryWithWords() {
        viewModelScope.launch (Dispatchers.IO) {
            createCategoryUseCase.createCategory(
                CategoryModel(
                    categoryName = categoryName,
                    image = categoryImage,
                ),
                _listWordInCategory.value
            )
            addWordsInStudyTableUseCase.addWordsInStudyTable(_listWordInCategory.value)
        }
    }
}