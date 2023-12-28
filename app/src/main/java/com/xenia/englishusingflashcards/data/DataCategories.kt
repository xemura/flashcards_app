package com.xenia.englishusingflashcards.data

data class CategoryInfo (
    var categoryName : String,
    var image : String,
    var words : List<WordInfo>,
    var progress: Float
)