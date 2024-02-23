package com.xenia.englishusingflashcards.domain.repository

import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<CategoryModel>?>
    fun getWordsFromCategory(categoryName: String): Flow<List<WordModel>?>

    fun deleteWordFromCategory(categoryName: String, word: String, translate: String, sentence: String)

    fun createCategory(category: CategoryModel, listWordsInCategory: List<WordModel>?)
    fun deleteCategory(category: CategoryModel)

    fun updateCategoryImage(oldImage: Int, newImage: Int, categoryName: String)
}