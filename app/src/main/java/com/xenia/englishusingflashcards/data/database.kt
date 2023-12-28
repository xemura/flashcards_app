package com.xenia.englishusingflashcards.data

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
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
    CardInfo("cardsThatIKnow слово", "cardsThatIKnow перевод", "предложение"),
)

val learnedCards = listOf(
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
    CardInfo("learnedCards слово", "learnedCards перевод", "предложение"),
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
    CategoryInfo("A1", "Город","", studyCards, 100.0f),
    CategoryInfo("A1", "Город","", studyCards, 100.0f),
    )
