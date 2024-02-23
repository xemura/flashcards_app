package com.xenia.englishusingflashcards.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xenia.englishusingflashcards.data.repository.LearnRepositoryImpl
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.usecases.learn_screen.DeleteWordFromStudyTableUseCase
import com.xenia.englishusingflashcards.domain.usecases.learn_screen.MoveToAnotherStateUseCase
import com.xenia.englishusingflashcards.domain.usecases.main_screen.GetWordsFromTableStudyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LearningViewModel(app: Application) : ViewModel() {

    private val repository = LearnRepositoryImpl(app)
    private val getWordsToStudyUseCase = GetWordsFromTableStudyUseCase(repository)
    private val deleteWordFromStudyTableUseCase = DeleteWordFromStudyTableUseCase(repository)
    private val moveToAnotherStateUseCase = MoveToAnotherStateUseCase(repository)

    private val _wordsToStudy: MutableStateFlow<List<WordsStudyModel>?> = MutableStateFlow(emptyList())
    val wordsToStudy: StateFlow<List<WordsStudyModel>?> = _wordsToStudy.asStateFlow()

    init {
        getWordsToStudy()
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

    fun deleteWordFromTableStudy(word: String, translate: String, sentence: String) {
        viewModelScope.launch {
            deleteWordFromStudyTableUseCase.deleteWordInStudyTable(word, translate, sentence)
        }
    }

    fun guessedCardAndMoveToKnowState(wordId: Int) {
        viewModelScope.launch {
            moveToAnotherStateUseCase.guessedCardAndMoveToKnowState(wordId)
        }
    }
}