package com.xenia.englishusingflashcards.navigation

import android.net.Uri
import com.google.gson.Gson
import com.xenia.englishusingflashcards.data.entities.Category

enum class Screen {
    SPLASH,
    MAIN,

    LEARNING_CARDS,

    CATEGORY,
    CATEGORY_NAME,
    CREATE_CATEGORY,
    EDIT_CATEGORY,
    SORT_WORDS_IN_CATEGORY,
}
sealed class NavigationItem(val route: String) {
    data object Splash : NavigationItem(Screen.SPLASH.name)
    data object Main : NavigationItem(Screen.MAIN.name)


    data object LearningCard : NavigationItem(Screen.LEARNING_CARDS.name)


    data object Category : NavigationItem(Screen.CATEGORY.name)
    data object CategoryMain : NavigationItem(Screen.CATEGORY_NAME.name)
    data object CreateCategory : NavigationItem(Screen.CREATE_CATEGORY.name)
    data object EditCategory : NavigationItem("${Screen.EDIT_CATEGORY.name}/{edit_category}")
    {
        fun getRouteWithArgs(category: com.xenia.englishusingflashcards.data.entities.Category) : String {
            val categoryJson = Gson().toJson(category)
            return "${Screen.EDIT_CATEGORY.name}/${categoryJson.encode()}"
        }
    }
    data object SortWordsInCategory : NavigationItem("${Screen.SORT_WORDS_IN_CATEGORY.name}/{sort_words_in_category}") {
        fun getRouteWithArgs(category: com.xenia.englishusingflashcards.data.entities.Category) : String {
            val categoryJson = Gson().toJson(category)
            return "${Screen.SORT_WORDS_IN_CATEGORY.name}/${categoryJson.encode()}"
        }
    }
}

fun String.encode() : String {
    return Uri.encode(this)
}