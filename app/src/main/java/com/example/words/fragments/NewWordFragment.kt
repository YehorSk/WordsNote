package com.example.words.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.words.MainActivity
import com.example.words.R
import com.example.words.databinding.FragmentNewWordBinding
import com.example.words.model.Word
import com.example.words.viewmodel.MainViewModel
import java.time.LocalDate
import java.util.Date


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
        val title = binding.wordText.text.toString()
        val meaning = binding.wordMeaning.text.toString()
        val partOfSpeech = binding.autoCompleteTextView.text.toString()
        val date = Date().date

        viewModel.insertWord(Word(0, title,meaning,partOfSpeech, LocalDate.now().toString()))
        findNavController().navigate(NewWordFragmentDirections.actionNewWordFragmentToMainFragment())
        Toast.makeText(context,"Word added",Toast.LENGTH_SHORT).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewWordBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setPartsOfSpeech()
    }

    private fun setPartsOfSpeech() {
        val parts = resources.getStringArray(R.array.partsOfSpeech)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,parts)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }


}