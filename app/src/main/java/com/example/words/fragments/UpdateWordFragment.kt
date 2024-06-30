package com.example.words.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.words.MainActivity
import com.example.words.R
import com.example.words.databinding.FragmentUpdateWordBinding
import com.example.words.model.Word
import com.example.words.viewmodel.MainViewModel


class UpdateWordFragment : Fragment(R.layout.fragment_update_word) {

    private var _binding : FragmentUpdateWordBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding.word = viewModel.current_word
        binding.goBackFAB.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.saveWordFAB.setOnClickListener{
            if(binding.wordText.text != null){
                var word = Word(viewModel.current_word!!.id,binding.wordText.text.toString(),binding.wordMeaning.text.toString())
                viewModel.updateWord(word)
                viewModel.current_word = word
                findNavController().navigateUp()
            }else{
                Toast.makeText(context,"Add title",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateWordBinding.inflate(inflater, container,false)
        return binding.root
    }

}