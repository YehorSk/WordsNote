package com.example.words.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.words.databinding.WordItemLayoutBinding
import com.example.words.fragments.MainFragmentDirections
import com.example.words.model.Word
import com.example.words.viewmodel.MainViewModel
import java.util.zip.Inflater

class WordsAdapter : RecyclerView.Adapter<WordsAdapter.MainViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(pos:Int, word: Word)
    }

    private var itemClickListener: ItemClickListener? = null

    fun setItemClickListener(itemClickListener: ItemClickListener?){
        this.itemClickListener = itemClickListener
    }

    class MainViewHolder(val binding: WordItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Word>(){
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.word == newItem.word &&
                    oldItem.meaning == newItem.meaning
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            WordItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.word = differ.currentList[position]
        holder.itemView.setOnClickListener{
            itemClickListener?.onItemClick(position,differ.currentList[position])
            val direction = MainFragmentDirections.actionMainFragmentToShowWordFragment()
            it.findNavController().navigate(direction)
        }
    }

}