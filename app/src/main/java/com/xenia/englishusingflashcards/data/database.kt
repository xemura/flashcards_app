package com.xenia.englishusingflashcards.data

fun getDataOfLevels() : ListLevels {
    val listLevels = ListLevels(listOf
        (
        Level("A1", categoryTestA1),
        Level("A2", categoryTestA2),
        Level("B1", categoryTestB1),
        Level("B2", categoryTestB2),
        Level("C1", categoryTestC1),
        Level("C2", categoryTestC2),
    )
    )
    return listLevels
}

val wordDetails = WordDetails("27.12.2023",
    0,
    0.0,
    0.0)

val studyCards = listOf(
    WordInfo("cat", "studyCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "studyCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "studyCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "studyCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "studyCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "studyCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "studyCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "studyCards кошка", "i love cat`s", wordDetails),
)

val cardsThatIKnow = listOf(
    WordInfo("cat", "cardsThatIKnow кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "cardsThatIKnow кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "cardsThatIKnow кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "cardsThatIKnow кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "cardsThatIKnow кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "cardsThatIKnow кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "cardsThatIKnow кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "cardsThatIKnow кошка", "i love cat`s", wordDetails),
)

val learnedCards = listOf(
    WordInfo("cat", "learnedCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "learnedCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "learnedCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "learnedCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "learnedCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "learnedCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "learnedCards кошка", "i love cat`s", wordDetails),
    WordInfo("cat", "learnedCards кошка", "i love cat`s", wordDetails),
)



val categoryTestA1 = listOf(
    CategoryInfo("Овощи", "", studyCards, 40.0f),
    CategoryInfo("Животные","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 90.0f),
    CategoryInfo("Овощи", "", studyCards, 40.0f),
    CategoryInfo("Животные","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    )

val categoryTestA2 = listOf(
    CategoryInfo("A2", "", studyCards, 40.0f),
    CategoryInfo("A2","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 90.0f),
    CategoryInfo("Овощи", "", studyCards, 40.0f),
    CategoryInfo("Животные","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
)

val categoryTestB1 = listOf(
    CategoryInfo("B1", "", studyCards, 40.0f),
    CategoryInfo("B1","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 90.0f),
    CategoryInfo("Овощи", "", studyCards, 40.0f),
    CategoryInfo("Животные","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
)

val categoryTestB2 = listOf(
    CategoryInfo("B2", "", studyCards, 40.0f),
    CategoryInfo("B2","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 90.0f),
    CategoryInfo("Овощи", "", studyCards, 40.0f),
    CategoryInfo("Животные","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
)

val categoryTestC1 = listOf(
    CategoryInfo("C1", "", studyCards, 40.0f),
    CategoryInfo("C1","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 90.0f),
    CategoryInfo("Овощи", "", studyCards, 40.0f),
    CategoryInfo("Животные","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
)

val categoryTestC2 = listOf(
    CategoryInfo("C2", "", studyCards, 40.0f),
    CategoryInfo("C2","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 90.0f),
    CategoryInfo("Овощи", "", studyCards, 40.0f),
    CategoryInfo("Животные","", studyCards, 10.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
    CategoryInfo("Город","", studyCards, 100.0f),
)