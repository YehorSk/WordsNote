package com.example.words.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.words.MainActivity
import com.example.words.R
import com.example.words.databinding.FragmentNewWordBinding
import com.example.words.model.Word
import com.example.words.viewmodel.MainViewModel


class NewWordFragment : Fragment(R.layout.fragment_new_word) {

    private var _binding : FragmentNewWordBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding.goBackFAB.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.saveWordFAB.setOnClickListener{
            saveWord()
        }
    }

    private fun saveWord() {
        var title = binding.wordText.text.toString()
        var meaning = binding.wordMeaning.text.toString()
        viewModel.insertWord(Word(0, title,meaning))
        findNavController().navigate(NewWordFragmentDirections.actionNewWordFragmentToMainFragment())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewWordBinding.inflate(inflater, container,false)
        return binding.root
    }


}