package com.xenia.englishusingflashcards.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xenia.englishusingflashcards.room.daos.CategoryDao
import com.xenia.englishusingflashcards.room.daos.LevelDao
import com.xenia.englishusingflashcards.room.daos.TableDao
import com.xenia.englishusingflashcards.room.entities.Category
import com.xenia.englishusingflashcards.room.entities.Level
import com.xenia.englishusingflashcards.room.entities.TableStudy
import com.xenia.englishusingflashcards.room.entities.TableWordsLearned
import com.xenia.englishusingflashcards.room.entities.TableWordsThatIKnow
import com.xenia.englishusingflashcards.room.entities.Word

@Database(entities =
[
    Word::class,
    Level::class,
    Category::class,
    TableStudy::class,
    TableWordsThatIKnow::class,
    TableWordsLearned::class,
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun levelDao(): LevelDao
    abstract fun categoryDao(): CategoryDao
    abstract fun tableDao(): TableDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}