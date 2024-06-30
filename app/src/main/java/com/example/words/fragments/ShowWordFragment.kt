package com.example.words.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.words.MainActivity
import com.example.words.R
import com.example.words.databinding.FragmentShowWordBinding
import com.example.words.viewmodel.MainViewModel

class ShowWordFragment : Fragment(R.layout.fragment_show_word) {

    private var _binding : FragmentShowWordBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val dispatcher = requireActivity().onBackPressedDispatcher
        dispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(ShowWordFragmentDirections.actionShowWordFragmentToMainFragment())
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding.word = viewModel.current_word

        binding.goBackFAB.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.deleteWordFAB.setOnClickListener{
            viewModel.deleteWord(viewModel.current_word!!)
            findNavController().navigateUp()
        }
        binding.editWordFAB.setOnClickListener{
            findNavController().navigate(ShowWordFragmentDirections.actionShowWordFragmentToUpdateWordFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowWordBinding.inflate(inflater,container,false)
        return binding.root
    }

}