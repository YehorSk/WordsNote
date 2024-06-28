package com.example.words.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.words.R
import com.example.words.databinding.FragmentShowWordBinding

class ShowWordFragment : Fragment(R.layout.fragment_show_word) {

    private var _binding : FragmentShowWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowWordBinding.inflate(inflater,container,false)
        return binding.root
    }

}