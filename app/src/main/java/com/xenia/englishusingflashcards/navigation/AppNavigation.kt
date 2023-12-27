package com.xenia.englishusingflashcards.navigation

enum class Screen {
    SPLASH,
    MAIN,

    LEARNING_CARDS,
    LEARNING_BACK_CARDS,

    CATEGORY,
    ADD_WORDS,
}
sealed class NavigationItem(val route: String) {
    data object Splash : NavigationItem(Screen.SPLASH.name)
    data object Main : NavigationItem(Screen.MAIN.name)


    data object LearningCard : NavigationItem(Screen.LEARNING_CARDS.name)
    data object LearningBackCard : NavigationItem(Screen.LEARNING_BACK_CARDS.name)

    data object Category : NavigationItem(Screen.CATEGORY.name)
    data object AddWords : NavigationItem(Screen.ADD_WORDS.name)
}