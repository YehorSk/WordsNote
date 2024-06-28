package com.example.words.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.words.model.Word

@Dao
interface WordsDAO {

    @Insert
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("SELECT * FROM words_table ORDER BY id")
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM words_table WHERE word LIKE :query OR meaning LIKE :query")
    fun searchWord(query: String?): LiveData<List<Word>>

}