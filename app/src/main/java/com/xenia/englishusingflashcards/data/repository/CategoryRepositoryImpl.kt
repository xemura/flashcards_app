package com.xenia.englishusingflashcards.data.repository

import android.app.Application
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.data.mapper.CategoryMapper
import com.xenia.englishusingflashcards.data.mapper.WordMapper
import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.models.WordsStudyModel
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(app: Application) : CategoryRepository {

    private val appDb = AppDatabase.getInstance(app)

    private val categoryDao = appDb.categoryDao()
    private val wordDao = appDb.wordDao()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val mapperWord = WordMapper()
    private val mapperCategory = CategoryMapper()


    override fun updateCategoryImage(oldImage: Int, newImage: Int, categoryName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategoryImage(oldImage, newImage, categoryName)
        }
    }

    override fun getCategories(): Flow<List<CategoryModel>?> =
        categoryDao.getCategories().map { category ->
            mapperCategory.mapCategory(category)
        }

    override fun getWordsFromCategory(categoryName: String): Flow<List<WordModel>?> =
        categoryDao.getWordsInCategory(categoryName).map {
            mapperWord.mapWord(it)
        }

    override fun deleteWordFromCategory(
        categoryName: String,
        word: String,
        translate: String,
        sentence: String
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.deleteWordInCategory(categoryName, word, translate, sentence)
        }
    }

    override fun createCategory(category: CategoryModel, listWordsInCategory: List<WordModel>?) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.createCategory(mapperCategory.mapCategoryToData(category))
            if (!listWordsInCategory.isNullOrEmpty()) {
                wordDao.insertAll(mapperWord.mapWordToData(listWordsInCategory)) //
            }
        }
    }

    override fun deleteCategory(category: CategoryModel) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.deleteCategory(mapperCategory.mapCategoryToData(category))
            categoryDao.deleteWordsFromCategory(category.categoryName)
        }
    }

    override fun setUpNotification() {
        TODO("Not yet implemented")
    }
}