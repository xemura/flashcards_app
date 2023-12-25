package com.xenia.englishusingflashcards.navigation

enum class Screen {
    SPLASH,

    MAIN,
    STUDY_CARDS,
    CARDS_THAT_I_KNOW,
    LEARNED_CARDS,

    LEARNING_CARDS,
    LEARNING_BACK_CARDS,

    CATEGORY,
    ADD_WORDS,
}
sealed class NavigationItem(val route: String) {
    data object Splash : NavigationItem(Screen.SPLASH.name)

    data object Main : NavigationItem(Screen.MAIN.name)
    data object StudyCards : NavigationItem(Screen.STUDY_CARDS.name)
    data object CardsThatIKnow : NavigationItem(Screen.CARDS_THAT_I_KNOW.name)
    data object LearnedCards : NavigationItem(Screen.LEARNED_CARDS.name)


    data object LearningCard : NavigationItem(Screen.LEARNING_CARDS.name)
    data object LearningBackCard : NavigationItem(Screen.LEARNING_BACK_CARDS.name)

    data object Category : NavigationItem(Screen.CATEGORY.name)
    data object AddWords : NavigationItem(Screen.ADD_WORDS.name)
}