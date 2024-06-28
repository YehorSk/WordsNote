package com.example.words.repository

import com.example.words.database.WordsDAO
import com.example.words.database.WordsDatabase
import com.example.words.model.Word

class WordsRepository(private val db: WordsDatabase) {

    suspend fun insertWord(word: Word) = db.getWordDao().insertWord(word)
    suspend fun updateWord(word: Word) = db.getWordDao().updateWord(word)
    suspend fun deleteWord(word: Word) = db.getWordDao().deleteWord(word)

    fun getAllWords() = db.getWordDao().getAllWords()
    fun searchWord(query: String?) = db.getWordDao().searchWord(query)


}