package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.data.repository.LearnRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.usecases.main_screen.GetWordsFromTableKnowUseCase
import com.xenia.englishusingflashcards.domain.usecases.main_screen.GetWordsFromTableLearnedUseCase
import com.xenia.englishusingflashcards.domain.usecases.main_screen.GetWordsFromTableStudyUseCase
import com.xenia.englishusingflashcards.domain.usecases.main_screen.UpdateStateWordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : ViewModel() {

    private val repository = LearnRepositoryImpl(app)
    private val getWordsToStudyUseCase = GetWordsFromTableStudyUseCase(repository)
    private val getWordsKnowUseCase = GetWordsFromTableKnowUseCase(repository)
    private val getWordsLearnedUseCase = GetWordsFromTableLearnedUseCase(repository)
    private val updateStateWordsUseCase = UpdateStateWordsUseCase(repository)

    private val _wordsToStudy: MutableStateFlow<List<WordsStudyModel>?> = MutableStateFlow(emptyList())
    val wordsToStudy: StateFlow<List<WordsStudyModel>?> = _wordsToStudy.asStateFlow()

    private val _wordsToKnow: MutableStateFlow<List<WordsStudyModel>?> = MutableStateFlow(emptyList())
    val wordsToKnow: StateFlow<List<WordsStudyModel>?> = _wordsToKnow.asStateFlow()

    private val _wordsToLearned: MutableStateFlow<List<WordsStudyModel>?> = MutableStateFlow(emptyList())
    val wordsToLearned: StateFlow<List<WordsStudyModel>?> = _wordsToLearned.asStateFlow()

    init {
        updateStateWords()
        getWordsToStudy()
        getWordsToKnow()
        getWordsToLearned()
    }

    fun getCountWordsToStudy(): Int {
        return _wordsToStudy.value?.size ?: 0
    }

    fun getCountWordsToKnow(): Int {
        return _wordsToKnow.value?.size ?: 0
    }

    fun getCountWordsToLearned(): Int {
        return _wordsToLearned.value?.size ?: 0
    }

    private fun getWordsToStudy() {
        viewModelScope.launch {
            getWordsToStudyUseCase.getWordsFromStudyTable("учить")
                .flowOn(Dispatchers.IO)
                .catch {

                }
                .collect { listWords ->
                    _wordsToStudy.value = listWords
                }
        }
    }

    private fun getWordsToKnow() {
        viewModelScope.launch {
            getWordsKnowUseCase.getWordsFromKnowTable("знаю")
                .flowOn(Dispatchers.IO)
                .catch {

                }
                .collect { listWords ->
                    _wordsToKnow.value = listWords
                }
        }
    }

    private fun getWordsToLearned() {
        viewModelScope.launch {
            getWordsLearnedUseCase.getWordsFromLearnedTable("выучено")
                .flowOn(Dispatchers.IO)
                .catch {

                }
                .collect { listWords ->
                    _wordsToLearned.value = listWords
                }
        }
    }

    private fun updateStateWords() {
        viewModelScope.launch {
            updateStateWordsUseCase.updateStateWords()
        }
    }
}