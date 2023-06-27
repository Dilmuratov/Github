package com.example.github.ui.menu

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.databinding.ItemRepositoryBinding

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
            try {
                binding.ivOwnerProfile.setImageBitmap(BitmapFactory.decodeFile(repository.owner.avatar_url))
            } catch (e: Exception) {
                binding.ivOwnerProfile.setImageResource(R.drawable.ic_github)
            }
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