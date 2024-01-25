package com.xenia.englishusingflashcards.domain.repository

import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.data.entities.Word

interface CategoryRepository {
    fun getWordsFromCategory(categoryName: String): List<Word>?
    fun deleteWordFromCategory(categoryName: String, word: String, translate: String, sentence: String)

    fun createCategory(category: Category, listWordsInCategory: List<Word>?)
    fun deleteCategory(category: Category)

    fun updateCategoryImage(oldImage: Int, newImage: Int, categoryName: String)
    fun updateCategoryName(oldName: String, newName: String)

    fun setUpNotification()
}