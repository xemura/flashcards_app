package com.xenia.englishusingflashcards.domain.models

data class WordModel(
    val id: Int = 0,
    val categoryName: String = "",
    val word : String = "",
    val translate : String = "",
    val sentence : String = ""
)