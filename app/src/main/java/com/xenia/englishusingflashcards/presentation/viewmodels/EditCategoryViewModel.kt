package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.data.repository.CategoryRepositoryImpl
import com.xenia.englishusingflashcards.data.repository.WordRepositoryImpl
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.domain.usecases.AddWordInCategoryUseCase
import com.xenia.englishusingflashcards.domain.usecases.DeleteWordFromCategoryUseCase
import com.xenia.englishusingflashcards.domain.usecases.GetWordsFromCategoryUseCase
import com.xenia.englishusingflashcards.domain.usecases.UpdateCategoryImageUseCase
import com.xenia.englishusingflashcards.domain.usecases.UpdateCategoryNameUseCase
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

    ///////////////

    private val repository = CategoryRepositoryImpl(app)
    private val addWordInCategoryUseCase = AddWordInCategoryUseCase(WordRepositoryImpl(app))
    private val deleteWordFromCategoryUseCase = DeleteWordFromCategoryUseCase(repository)
    private val getWordsFromCategoryUseCase = GetWordsFromCategoryUseCase(repository)
    private val updateCategoryImageUseCase = UpdateCategoryImageUseCase(repository)
    private val updateCategoryNameUseCase = UpdateCategoryNameUseCase(repository)


    fun updateListWordsInCategory(word: Word) {
        viewModelScope.launch (Dispatchers.IO) {
            val list = _listWordInCategory.value?.toMutableList()
            list?.add(word.copy())
            _listWordInCategory.postValue(list)

            addWordInCategoryUseCase.addWordInCategory(word)
        }
    }

    fun updateImageCategory(oldImage: Int, newImage: Int, categoryName: String) {
        updateCategoryImageUseCase.updateCategoryImage(oldImage, newImage, categoryName)
    }

    fun updateCategoryName(oldName: String, newName: String) {
        updateCategoryNameUseCase.updateCategoryName(oldName, newName)
    }

    fun deleteWordInCategory(categoryName: String, word: String, translate: String, sentence: String) {
        viewModelScope.launch (Dispatchers.IO) {
            deleteWordFromCategoryUseCase.deleteWordFromCategory(categoryName, word, translate, sentence)
            val list = _listWordInCategory.value?.toMutableList()
            list?.removeIf { ((it.word == word) and (it.translate == translate) and (it.sentence == sentence)) }
            Log.d("Tag", "list = $list")
            _listWordInCategory.postValue(list)
        }
    }

    fun getListWordsInCategory(categoryName: String) {
        _listWordInCategory.value = getWordsFromCategoryUseCase.getWordsFromCategoryCategory(categoryName)
    }
}