package com.xenia.englishusingflashcards.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.xenia.englishusingflashcards.data.levelA1
import com.xenia.englishusingflashcards.data.levelA2
import com.xenia.englishusingflashcards.data.levelB1
import com.xenia.englishusingflashcards.data.levelB2
import com.xenia.englishusingflashcards.data.levelC1
import com.xenia.englishusingflashcards.data.levelC2
import com.xenia.englishusingflashcards.repositories.CategoryRepository
import com.xenia.englishusingflashcards.room.database.AppDatabase
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Level
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(app: Application) : ViewModel() {

    var getCurrentLevel = "A1"

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val categoryRepository: CategoryRepository

    private var categoriesInLevel : Level? = null
    private var wordsInCategory : Category? = null

    init {
        val appDb = AppDatabase.getInstance(app)
        val categoryDao = appDb.categoryDao()
        appDb.levelDao().insertAll(levelA1)
        appDb.levelDao().insertAll(levelA2)
        appDb.levelDao().insertAll(levelB1)
        appDb.levelDao().insertAll(levelB2)
        appDb.levelDao().insertAll(levelC1)
        appDb.levelDao().insertAll(levelC2)
        categoryRepository = CategoryRepository(categoryDao)
    }

    fun getCategoriesByLevel(levelName: String) : Level? {
        coroutineScope.launch(Dispatchers.IO) {
            categoriesInLevel = categoryRepository.getCategoriesByLevel(levelName)
        }
        return categoriesInLevel
    }

    fun getWordsInCategory(levelName: String, categoryName: String) : Category? {
        coroutineScope.launch(Dispatchers.IO) {
            wordsInCategory = categoryRepository.getWordsInCategory(levelName, categoryName)
        }
        return wordsInCategory
    }
}