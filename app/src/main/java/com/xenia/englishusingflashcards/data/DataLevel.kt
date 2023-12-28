package com.xenia.englishusingflashcards.data

data class ListLevels(
    var levels: List<Level>
)

data class Level (
    var level : String,
    var categories : List<CategoryInfo>,
)