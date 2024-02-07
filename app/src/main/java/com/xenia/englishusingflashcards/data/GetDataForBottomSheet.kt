package com.xenia.englishusingflashcards.data

import com.xenia.englishusingflashcards.data.entities.Word

data class BottomSheetInfo (
    var data : List<Word>,
    var header : String,
    var description: String,
)

fun getBottomSheetInfo(text: String) : BottomSheetInfo {
    val bottomSheetInfo = BottomSheetInfo(listOf(), "", "")
    when (text) {
        "учить" -> {
            bottomSheetInfo.data = listOf(
                Word(0, "", "1", "", "", false, "",
                0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
                Word(0, "", "1", "", "", false, "",
                    0, 0.0, 0.0),
            )
            bottomSheetInfo.header = "Карточки для изучения"
            bottomSheetInfo.description = "Это число означает, сколько карточек готово к изучению."
        }
        "знаю" -> {
            bottomSheetInfo.data = listOf(
                Word(1, "","1", "", "", false, "",
                0, 0.0, 0.0)
            )
            bottomSheetInfo.header = "Краткосрочная память"
            bottomSheetInfo.description = "Это число означает, сколько слов и фраз у тебя в кратковременной памяти."
        }
        "выучено" -> {
            bottomSheetInfo.data = listOf(
                Word(2, "","1", "", "", false, "",
                0, 0.0, 0.0)
            )
            bottomSheetInfo.header = "Долгосрочная память"
            bottomSheetInfo.description = "Это число означает, сколько слов и фраз у тебя в долговременной памяти."
        }
    }
    return bottomSheetInfo
}