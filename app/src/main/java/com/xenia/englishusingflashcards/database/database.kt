package com.xenia.englishusingflashcards.database

data class CardInfo(
    var word : String,
    var translate : String,
    var sentence : String
)

val studyCards = listOf(
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
    CardInfo("studyCards слово", "studyCards перевод", "предложение"),
)

val cardsThatIKnow = listOf(
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "studyCards перевод", "предложение"),
)

val learnedCards = listOf(
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
    CardInfo("learnedCards слово", "studyCards перевод", "предложение"),
)

data class CategoryInfo(
    var level : String,
    var categoryName : String,
    var image : String,
    var words : List<CardInfo>,
    var progress: Float
)

val categoryTest = listOf(
    CategoryInfo("A1", "Овощи", "", studyCards, 40.0f),
    CategoryInfo("A1", "Животные","", studyCards, 10.0f),
    CategoryInfo("A1", "Город","", studyCards, 90.0f),
    CategoryInfo("A1", "Овощи", "", studyCards, 40.0f),
    CategoryInfo("A1", "Животные","", studyCards, 10.0f),
    CategoryInfo("A1", "Город","", studyCards, 100.0f),
    )
