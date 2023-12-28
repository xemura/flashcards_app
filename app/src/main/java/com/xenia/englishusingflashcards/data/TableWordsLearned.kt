package com.xenia.englishusingflashcards.data

data class TableWordsLearned (
    var words : List<WordInfo>,
    var countOfWords : Int = words.size,
)