package com.example.words.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.model.Word
import com.example.words.repository.WordsRepository
import kotlinx.coroutines.launch

class MainViewModel(app: Application,private val repository: WordsRepository) : AndroidViewModel(app) {

    var current_word : Word? = null

    fun insertWord(word: Word) = viewModelScope.launch {
        repository.insertWord(word)
    }

    fun updateWord(word: Word) = viewModelScope.launch {
        repository.updateWord(word)
    }

    fun deleteWord(word: Word) = viewModelScope.launch {
        repository.deleteWord(word)
    }

    fun getAllWords() = repository.getAllWords()
    fun searchWord(query: String?) = repository.searchWord(query)


}