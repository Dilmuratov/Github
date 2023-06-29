package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.databinding.ItemRepositoryMainBinding
import com.example.github.utils.set

class RepositoryMainAdapter : ListAdapter<RepositoryData, RepositoryMainAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<RepositoryData>() {
        override fun areItemsTheSame(oldItem: RepositoryData, newItem: RepositoryData) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: RepositoryData, newItem: RepositoryData) =
            oldItem.id == newItem.id
    }
) {

    inner class ViewHolder(private val binding: ItemRepositoryMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            binding.tvRepositoryName.text = item.name
            binding.tvOwnerUsername.text = item.owner.login
            binding.ivOwnerProfile.set(item.owner.avatar_url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRepositoryMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}