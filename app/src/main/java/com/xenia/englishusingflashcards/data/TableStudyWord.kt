package com.xenia.englishusingflashcards.data

data class StudyTable(
    var words : List<WordInfo>,
    var countOfWords : Int = words.size,
)