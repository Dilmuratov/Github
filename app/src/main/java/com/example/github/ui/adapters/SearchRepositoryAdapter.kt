package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.data.models.searchrepositories.RepositoryItem
import com.example.github.databinding.ItemSearchedRepositoryBinding
import com.example.github.utils.set

class SearchRepositoryAdapter : ListAdapter<RepositoryItem, SearchRepositoryAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<RepositoryItem>() {
        override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem) =
            oldItem.id == newItem.id
    }
) {

    inner class ViewHolder(private val binding: ItemSearchedRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            binding.tvRepositoryName.text = item.name
            binding.tvOwnerUsername.text = item.owner.login
            binding.ivOwnerProfile.set(item.owner.avatar_url)
            binding.tvRepositoryDescription.text = item.description
            binding.tvLanguages.text = item.language
            binding.tvCountOfStars.text = item.stargazers_count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSearchedRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}