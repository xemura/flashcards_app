package com.xenia.englishusingflashcards.presentation.main_screen.bottom_sheets

import com.xenia.englishusingflashcards.presentation.model.BottomSheetInfo
import com.xenia.englishusingflashcards.presentation.viewmodels.MainViewModel

fun getBottomSheetInfo(text: String, mainViewModel: MainViewModel) : BottomSheetInfo {
    val bottomSheetInfo = BottomSheetInfo(listOf(), "", "")

    when (text) {
        "учить" -> {
            bottomSheetInfo.data = mainViewModel.wordsToStudy.value
            bottomSheetInfo.header = "Карточки для изучения"
            bottomSheetInfo.description = "Это число означает, сколько карточек готово к изучению."
        }
        "знаю" -> {
            bottomSheetInfo.data = null
            bottomSheetInfo.header = "Краткосрочная память"
            bottomSheetInfo.description = "Это число означает, сколько слов и фраз у тебя в кратковременной памяти."
        }
        "выучено" -> {
            bottomSheetInfo.data = null
            bottomSheetInfo.header = "Долгосрочная память"
            bottomSheetInfo.description = "Это число означает, сколько слов и фраз у тебя в долговременной памяти."
        }
    }
    return bottomSheetInfo
}