package com.xenia.englishusingflashcards.data

data class TableWordsKnown (
    var words : List<WordInfo>,
    var countOfWords : Int = words.size,
)