package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xenia.englishusingflashcards.data.repository.CategoryRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.usecases.GetWordsToLearnUseCase

class LearningViewModel(app: Application) : ViewModel() {

    private val repository = CategoryRepositoryImpl(app)
    private val getWordsToLearnUseCase = GetWordsToLearnUseCase(repository)

    private val _listLearnWords = MutableLiveData<WordsStudyModel?>()
    val listLearnWords: LiveData<WordsStudyModel?>
        get() = _listLearnWords


    init {
        getWordToStudy()
    }

    fun getWordToStudy() {
        _listLearnWords.value = getWordsToLearnUseCase.getWordsToLearn()
    }
}