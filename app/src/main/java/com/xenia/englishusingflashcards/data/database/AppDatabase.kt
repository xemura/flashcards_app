package com.xenia.englishusingflashcards.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xenia.englishusingflashcards.data.daos.CategoryDao
import com.xenia.englishusingflashcards.data.daos.LearnDao
import com.xenia.englishusingflashcards.data.daos.WordDao
import com.xenia.englishusingflashcards.data.entities.Category
import com.xenia.englishusingflashcards.data.entities.NotificationTable
import com.xenia.englishusingflashcards.data.entities.Word

@Database(entities =
[
    Word::class,
    Category::class,
    NotificationTable::class,
], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun wordDao(): WordDao

    abstract fun learnDao(): LearnDao
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