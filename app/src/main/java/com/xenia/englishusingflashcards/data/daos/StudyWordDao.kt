package com.xenia.englishusingflashcards.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xenia.englishusingflashcards.data.entities.TableStudyWord
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyWordDao {

    @Query("DELETE FROM table_study WHERE " +
            "word = :word AND " +
            "translate=:translate AND " +
            "sentence=:sentence")
    suspend fun deleteWordInStudyTable(word: String, translate: String, sentence: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWordInStudyTable(tableStudyWord: TableStudyWord)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAll(list: List<TableStudyWord>)

    @Query("SELECT * FROM table_study")
    fun getWordsFromStudyTable(): Flow<List<TableStudyWord>?>

    @Query("SELECT * FROM table_study WHERE id=:wordId")
    suspend fun getWordFromStudyTable(wordId: Int): TableStudyWord

    @Query("UPDATE table_study SET " +
            "date_of_the_next_repetition=:nextDate, " +
            "the_number_of_repetitions=:number, " +
            "state=:state, " +
            "the_repetition_interval=:interval " +
            "WHERE id=:wordId")
    fun guessedCardAndMoveToKnowState(
        wordId: Int,
        nextDate: String,
        number: Int,
        state: String,
        interval: String
        )
}