package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.data.models.HistoryOfSearchData
import com.example.github.databinding.ItemHistoryOfSearchBinding

class HistoryAdapter : ListAdapter<HistoryOfSearchData, HistoryAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<HistoryOfSearchData>() {
        override fun areItemsTheSame(
            oldItem: HistoryOfSearchData,
            newItem: HistoryOfSearchData
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: HistoryOfSearchData,
            newItem: HistoryOfSearchData
        ) = oldItem.id == newItem.id || oldItem.query == newItem.query
    }
) {

    inner class ViewHolder(private val binding: ItemHistoryOfSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            binding.tvQuery.text = item.query

            binding.ivCancel.setOnClickListener {
                onClearClickListener?.invoke(item)
            }
        }
    }

    private var onClearClickListener: ((HistoryOfSearchData) -> (Unit))? = null
    fun setOnClearClickListener(block: ((HistoryOfSearchData) -> (Unit))) {
        onClearClickListener = block
    }

    fun removeItem(item: HistoryOfSearchData){
        val list = currentList.toMutableList()
        list.remove(item)
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemHistoryOfSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}