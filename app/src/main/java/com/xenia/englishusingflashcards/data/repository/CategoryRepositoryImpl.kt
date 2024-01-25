package com.xenia.englishusingflashcards.data.repository

import android.app.Application
import com.xenia.englishusingflashcards.data.database.AppDatabase
import com.xenia.englishusingflashcards.data.entities.Word
import com.xenia.englishusingflashcards.data.mapper.CategoryMapper
import com.xenia.englishusingflashcards.data.mapper.WordMapper
import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.models.WordModel
import com.xenia.englishusingflashcards.domain.repository.CategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryRepositoryImpl(app: Application) : CategoryRepository {

    private val appDb = AppDatabase.getInstance(app)
    private val categoryDao = appDb.categoryDao()
    private val wordDao = appDb.wordDao()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var wordsInCategory: List<Word>? = null

    private val mapperWord = WordMapper()
    private val mapperCategory = CategoryMapper()


    override fun updateCategoryName(oldName: String, newName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategoryName(oldName, newName)
            wordDao.updateCategoryInWords(newName, oldName)
        }
    }

    override fun updateCategoryImage(oldImage: Int, newImage: Int, categoryName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategoryImage(oldImage, newImage, categoryName)
        }
    }

    override fun getWordsFromCategory(categoryName: String): List<WordModel> {
        coroutineScope.launch(Dispatchers.IO) {
            wordsInCategory = categoryDao.getWordsInCategory(categoryName)
        }
        return mapperWord.mapWord(wordsInCategory)
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

//    override fun getCategories(): Flow<List<CategoryModel>?> {
//        val list = coroutineScope.async {
//            appDb.categoryDao().getCategories()
//        }
//        return mapperCategory.mapCategory(list.await())
////        coroutineScope.launch(Dispatchers.IO) {
////            appDb.categoryDao().getCategories().collect { it ->
////                return mapperCategory.mapCategory(it)
////            }
////            return mapperCategory.mapCategory(appDb.categoryDao().getCategories().collect())
////        }
//    }

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