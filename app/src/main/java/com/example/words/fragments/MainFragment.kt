package com.example.words.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.words.MainActivity
import com.example.words.R
import com.example.words.adapter.WordsAdapter
import com.example.words.databinding.FragmentMainBinding
import com.example.words.model.Word
import com.example.words.viewmodel.MainViewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel : MainViewModel
    private lateinit var noteAdapter : WordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()
        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    searchWord(s.toString())
                }
            }
        })
        binding.addWordFAB.setOnClickListener{
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToNewWordFragment())
        }
    }

    private fun setUpRecyclerView() {
        noteAdapter = WordsAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = noteAdapter
        }
        activity?.let {
            mainViewModel.getAllWords().observe(viewLifecycleOwner, {
                note -> noteAdapter.differ.submitList(note)
            })
        }

        noteAdapter.setItemClickListener(object : WordsAdapter.ItemClickListener{
            override fun onItemClick(pos: Int, word: Word) {
                mainViewModel.current_word = word
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun searchWord(newText: String) {
        val searchQuery = "%$newText%"
        mainViewModel.searchWord(searchQuery).observe(
            this,
            {list -> noteAdapter.differ.submitList(list)}
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}