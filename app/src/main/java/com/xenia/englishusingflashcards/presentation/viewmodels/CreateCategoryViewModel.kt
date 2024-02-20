package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.R
import com.xenia.englishusingflashcards.data.repository.CategoryRepositoryImpl
import com.xenia.englishusingflashcards.data.repository.LearnRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.usecases.learn_screen.AddWordsInStudyTableUseCase
import com.xenia.englishusingflashcards.domain.usecases.category_screen.CreateCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateCategoryViewModel(app: Application) : ViewModel() {

    var categoryName by mutableStateOf("")
        private set

    var categoryImage by mutableIntStateOf(R.drawable.image_1)
        private set

    private val _listWordsInCategory = MutableStateFlow(emptyList<WordModel>())
    val listWordsInCategory: StateFlow<List<WordModel>?> = _listWordsInCategory.asStateFlow()


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
            val list = _listWordsInCategory.value.toMutableList()
            list.add(word.copy())
            _listWordsInCategory.value = list
        }
    }

    fun deleteWordInCategory(word: String, translate: String, sentence: String) {
        viewModelScope.launch (Dispatchers.IO) {
            val list = _listWordsInCategory.value.toMutableList()
            list.removeIf { ((it.word == word) and (it.translate == translate) and (it.sentence == sentence)) }
            _listWordsInCategory.value = list
        }
    }

    fun saveCategoryWithWords() {
        viewModelScope.launch (Dispatchers.IO) {
            createCategoryUseCase.createCategory(
                CategoryModel(
                    categoryName = categoryName,
                    image = categoryImage,
                ),
                _listWordsInCategory.value
            )
            addWordsInStudyTableUseCase.addWordsInStudyTable(_listWordsInCategory.value)
        }
    }
}