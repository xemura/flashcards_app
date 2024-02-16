package com.xenia.englishusingflashcards.presentation.main_screen.bottom_sheets

import com.xenia.englishusingflashcards.domain.models.WordsStudyModel

data class BottomSheetInfo (
    var data : List<WordsStudyModel>?,
    var header : String,
    var description: String,
)
