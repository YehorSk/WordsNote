package com.example.words

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.words.database.WordsDatabase
import com.example.words.databinding.ActivityMainBinding
import com.example.words.model.Word
import com.example.words.repository.WordsRepository
import com.example.words.viewmodel.MainViewModel
import com.example.words.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        viewModel.insertWord(Word(0,"Katze","Cat"))
        viewModel.insertWord(Word(0,"Hund","Dog"))
    }

    private fun setUpViewModel() {
        val wordsRepository = WordsRepository(WordsDatabase(this))
        val viewModelProviderFactory = MainViewModelFactory(application, wordsRepository)
        viewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(MainViewModel::class.java)
    }
}