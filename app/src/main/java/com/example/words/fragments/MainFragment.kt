package com.example.words.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.words.MainActivity
import com.example.words.R
import com.example.words.adapter.WordsAdapter
import com.example.words.databinding.FragmentMainBinding
import com.example.words.model.Word
import com.example.words.viewmodel.MainViewModel


class MainFragment : Fragment(R.layout.fragment_main), SearchView.OnQueryTextListener {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel : MainViewModel
    private lateinit var adapter : WordsAdapter
    private lateinit var mView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()
        mView = view
    }

    private fun setUpRecyclerView() {
        adapter = WordsAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(mView.context,LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapter
        }
        activity?.let {
            mainViewModel.getAllWords().observe(viewLifecycleOwner, {
                note -> adapter.differ.submitList(note)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_main,menu)
        val mMenuSearch = menu.findItem(R.id.menuSearch).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = false
        mMenuSearch.setOnQueryTextListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchWord(newText)
        }
        return true
    }

    private fun searchWord(newText: String) {
        val searchQuery = "%$newText%"
        mainViewModel.searchWord(searchQuery).observe(
            this,
            {list -> adapter.differ.submitList(list)}
            )
    }

}