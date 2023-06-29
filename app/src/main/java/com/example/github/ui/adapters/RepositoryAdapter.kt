package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.databinding.ItemRepositoryBinding
import com.example.github.utils.set

class RepositoryAdapter :
    ListAdapter<RepositoryData, RepositoryAdapter.RepositoryViewHolder>(
        object : DiffUtil.ItemCallback<RepositoryData>() {
            override fun areItemsTheSame(
                oldItem: RepositoryData,
                newItem: RepositoryData
            ) =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: RepositoryData,
                newItem: RepositoryData
            ) =
                oldItem.id == newItem.id
        }
    ) {

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val repository = getItem(position)
            binding.tvOwnerUsername.text = repository.owner.login
            binding.ivOwnerProfile.set(repository.owner.avatar_url)
            binding.tvCountOfStars.text = repository.stargazers_count.toString()
            binding.tvLanguages.text = repository.language
            binding.tvRepositoryName.text = repository.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepositoryViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(position)
    }
}