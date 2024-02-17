package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.data.repository.LearnRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.usecases.GetWordsFromStudyTableUseCase
import kotlinx.coroutines.launch

class LearningViewModel(app: Application) : ViewModel() {

    private val repository = LearnRepositoryImpl(app)
    private val getWordsToLearnUseCase = GetWordsFromStudyTableUseCase(repository)

    private val _listLearnWords = MutableLiveData<List<WordsStudyModel>?>()
    val listLearnWords: LiveData<List<WordsStudyModel>?>
        get() = _listLearnWords


    init {
        getWordToStudy()
        Log.d("LearningViewModel", _listLearnWords.value.toString())
    }

    fun getWordToStudy() {
        viewModelScope.launch {
            _listLearnWords.value = getWordsToLearnUseCase.getWordsFromStudyTable()
        }
    }
}