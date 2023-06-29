package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.data.models.searchusers.UserItem
import com.example.github.databinding.ItemSearchedPeopleBinding
import com.example.github.utils.set

class SearchUserAdapter : ListAdapter<UserItem, SearchUserAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<UserItem>() {
        override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem) =
            oldItem.id == newItem.id
    }
) {

    inner class ViewHolder(private val binding: ItemSearchedPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            binding.ivUserProfile.set(item.avatar_url)
            binding.tvUsername.text = item.login
            binding.tvFullName.text = item.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSearchedPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}