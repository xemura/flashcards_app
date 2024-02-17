package com.xenia.englishusingflashcards.presentation.model

import com.xenia.englishusingflashcards.domain.models.WordsStudyModel

data class BottomSheetInfo (
    var data : List<WordsStudyModel>?,
    var header : String,
    var description: String,
)
