package com.xenia.englishusingflashcards.navigation

enum class Screen {
    SPLASH,
    MAIN,

    LEARNING_CARDS,

    CATEGORY,
    CREATE_CATEGORY,
}
sealed class NavigationItem(val route: String) {
    data object Splash : NavigationItem(Screen.SPLASH.name)
    data object Main : NavigationItem(Screen.MAIN.name)


    data object LearningCard : NavigationItem(Screen.LEARNING_CARDS.name)


    data object Category : NavigationItem(Screen.CATEGORY.name)
    data object CreateCategory : NavigationItem(Screen.CREATE_CATEGORY.name)
}