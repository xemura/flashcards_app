package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.data.repository.LearnRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.usecases.learn_screen.GetWordsFromStudyTableUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : ViewModel() {

    private val repository = LearnRepositoryImpl(app)
    private val getWordsToLearnUseCase = GetWordsFromStudyTableUseCase(repository)

    private val _wordsToStudy: MutableStateFlow<List<WordsStudyModel>?> = MutableStateFlow(emptyList())
    val wordsToStudy: StateFlow<List<WordsStudyModel>?> = _wordsToStudy.asStateFlow()

    init {
        getWordsToStudy()
    }

    fun getCountWordsToStudy(): Int {
        return _wordsToStudy.value?.size ?: 0
    }

    private fun getWordsToStudy() {
        viewModelScope.launch {
            getWordsToLearnUseCase.getWordsFromStudyTable()
                .flowOn(Dispatchers.IO)
                .catch {

                }
                .collect { listWords ->
                    Log.d("MainViewModel", "collect")
                    _wordsToStudy.value = listWords
                }
        }
    }
}