package com.example.words.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.words.model.Word

@Database(entities = [Word::class], version = 1)
abstract class WordsDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordsDAO

    companion object{
        @Volatile
        private var instance: WordsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
            synchronized(LOCK){
                instance ?:
                createDatabase(context).also{
                    instance = it
                }
            }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                WordsDatabase::class.java,
                "words_database"
            ).build()

    }
}