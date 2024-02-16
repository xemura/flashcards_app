package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.repository.LearnRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.usecases.GetWordsFromStudyTableUseCase

class MainViewModel(app: Application) : ViewModel() {

    private val appDb: AppDatabase = AppDatabase.getInstance(app)
    private val repository = LearnRepositoryImpl(app)
    private val getWordsToLearnUseCase = GetWordsFromStudyTableUseCase(repository)

    fun getCountWordsToStudy(): Int {
        return getWordsToLearnUseCase.getWordsFromStudyTable()?.size ?: 0
    }

    fun getWordsToStudy(): List<WordsStudyModel>? {
        return getWordsToLearnUseCase.getWordsFromStudyTable()
    }
}