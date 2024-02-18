package com.xenia.englishusingflashcards.data.mapper

import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.domain.models.CategoryModel
import com.xenia.englishusingflashcards.domain.models.WordModel

class CategoryMapper {

    fun mapCategoryToData(category: CategoryModel): Category {

        return Category(
            id = category.id,
            categoryName = category.categoryName,
            image = category.image
        )
    }

    fun mapCategory(categories: List<Category>?): List<CategoryModel> {

        val list = mutableListOf<CategoryModel>()
        if (categories != null) {
            for (category in categories) {
                val categoryModel = CategoryModel(
                    id = category.id,
                    categoryName = category.categoryName,
                    image = category.image
                )
                list.add(categoryModel)
            }
        }
        return list
    }

    fun mapCategoryToCategoryModel(categories: List<Category>?): List<CategoryModel> {

        val list = mutableListOf<CategoryModel>()
        if (categories != null) {
            for (category in categories) {
                val categoryModel = CategoryModel(
                    id = category.id,
                    categoryName = category.categoryName,
                    image = category.image
                )
                list.add(categoryModel)
            }
        }
        return list
    }
}