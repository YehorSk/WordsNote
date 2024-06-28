package com.example.words.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.words.repository.WordsRepository

class MainViewModelFactory(val app: Application, private val repository: WordsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(app,repository) as T
    }

}