package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.data.repository.CategoryRepositoryImpl
import com.xenia.englishusingflashcards.data.repository.LearnRepositoryImpl
import com.xenia.englishusingflashcards.data.repository.WordRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.usecases.category_screen.AddWordInCategoryUseCase
import com.xenia.englishusingflashcards.domain.usecases.learn_screen.AddWordInStudyTableUseCase
import com.xenia.englishusingflashcards.domain.usecases.category_screen.DeleteWordFromCategoryUseCase
import com.xenia.englishusingflashcards.domain.usecases.learn_screen.DeleteWordFromStudyTableUseCase
import com.xenia.englishusingflashcards.domain.usecases.category_screen.GetWordsFromCategoryUseCase
import com.xenia.englishusingflashcards.domain.usecases.category_screen.UpdateCategoryImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class EditCategoryViewModel(app: Application, name: String) : ViewModel() {

    var categoryName: String = ""

    private val _categoryImage = MutableLiveData<Int>()
    val categoryImage : LiveData<Int> = _categoryImage

    fun updateCategoryImage(input: Int) {
        _categoryImage.value = input
    }

    private val _listWordInCategory: MutableStateFlow<List<WordModel>?> = MutableStateFlow(emptyList())
    val listWordInCategory: StateFlow<List<WordModel>?> = _listWordInCategory.asStateFlow()

    private val repository = CategoryRepositoryImpl(app)
    private val addWordInCategoryUseCase = AddWordInCategoryUseCase(WordRepositoryImpl(app))
    private val deleteWordFromCategoryUseCase = DeleteWordFromCategoryUseCase(repository)
    private val getWordsFromCategoryUseCase = GetWordsFromCategoryUseCase(repository)
    private val updateCategoryImageUseCase = UpdateCategoryImageUseCase(repository)

    private val repositoryLearn = LearnRepositoryImpl(app)
    private val addWordInStudyTableUseCase = AddWordInStudyTableUseCase(repositoryLearn)
    private val deleteWordInStudyTableUSeCase = DeleteWordFromStudyTableUseCase(repositoryLearn)

    init {
        categoryName = name
        getListWordsInCategory(name)
    }

    fun updateListWordsInCategory(word: WordModel) {
        viewModelScope.launch (Dispatchers.IO) {
            addWordInCategoryUseCase.addWordInCategory(word)
            addWordInStudyTableUseCase.addWordInStudyTable(word)
        }
    }

    fun updateImageCategory(oldImage: Int, newImage: Int, categoryName: String) {
        updateCategoryImageUseCase.updateCategoryImage(oldImage, newImage, categoryName)
    }

    fun deleteWordInCategory(categoryName: String, word: String, translate: String, sentence: String) {
        viewModelScope.launch (Dispatchers.IO) {
            deleteWordFromCategoryUseCase.deleteWordFromCategory(categoryName, word, translate, sentence)
            deleteWordInStudyTableUSeCase.deleteWordInStudyTable(word, translate, sentence)
        }
    }

    private fun getListWordsInCategory(categoryName: String) {
        viewModelScope.launch {
            getWordsFromCategoryUseCase.getWordsFromCategory(categoryName)
                .flowOn(Dispatchers.IO)
                .catch {

                }
                .collect { listWords ->
                    _listWordInCategory.value = listWords
                }
        }
    }
}