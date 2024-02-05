package com.xenia.englishusingflashcards.domain.models


data class WordsStudyModel (
    val wordsListToStudy: List<WordModel>,
    val countWords: Int
)