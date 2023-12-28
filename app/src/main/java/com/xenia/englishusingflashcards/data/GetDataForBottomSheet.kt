package com.xenia.englishusingflashcards.data

data class BottomSheetInfo (
    var data : List<CardInfo>,
    var header : String,
    var description: String,
)

fun getBottomSheetInfo(text: String) : BottomSheetInfo {
    val bottomSheetInfo = BottomSheetInfo(listOf(), "", "")
    when (text) {
        "учить" -> {
            bottomSheetInfo.data = studyCards
            bottomSheetInfo.header = "Карточки для изучения"
            bottomSheetInfo.description = "Это число означает, сколько карточек готово к изучению."
        }
        "знаю" -> {
            bottomSheetInfo.data = cardsThatIKnow
            bottomSheetInfo.header = "Краткосрочная память"
            bottomSheetInfo.description = "Это число означает, сколько слов и фраз у тебя в кратковременной памяти."
        }
        "выучено" -> {
            bottomSheetInfo.data = learnedCards
            bottomSheetInfo.header = "Долгосрочная память"
            bottomSheetInfo.description = "Это число означает, сколько слов и фраз у тебя в долговременной памяти."
        }
    }
    return bottomSheetInfo
}