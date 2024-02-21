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
    fun getWordsFromStudyTable(): List<TableStudyWord>?

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

    @Query("SELECT * FROM table_study WHERE state=:study")
    fun getWordsToStudy(study: String): Flow<List<TableStudyWord>?>

    @Query("SELECT * FROM table_study WHERE state=:know")
    fun getWordsToKnow(know: String): Flow<List<TableStudyWord>?>

    @Query("SELECT * FROM table_study WHERE state=:learned")
    fun getWordsToLearned(learned: String): Flow<List<TableStudyWord>?>

    @Query("UPDATE table_study SET " +
            "date_of_the_next_repetition=:nextDate, " +
            "state=:state " +
            "WHERE id=:wordId")
    fun updateDateOfTheNextRepetition(wordId: Int, nextDate: String, state: String)

    @Query("UPDATE table_study SET " +
            "state=:state " +
            "WHERE id=:wordId")
    fun updateState(wordId: Int, state: String)

    @Query("UPDATE table_study SET " +
            "the_number_of_repetitions=:number, " +
            "state=:state, " +
            "the_repetition_interval=:interval " +
            "WHERE id=:wordId")
    fun updateRepetitionIntervalAndNumberOfRepetition(
        wordId: Int,
        state: String,
        number: Int,
        interval: String
    )
}