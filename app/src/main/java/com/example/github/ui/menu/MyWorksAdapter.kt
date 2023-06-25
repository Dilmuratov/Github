package com.example.github.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.data.models.MyWorksData
import com.example.github.databinding.ItemMyWorkBinding

class MyWorksAdapter : ListAdapter<MyWorksData, MyWorksAdapter.MyWorkViewHolder>(
    object : DiffUtil.ItemCallback<MyWorksData>() {
        override fun areItemsTheSame(oldItem: MyWorksData, newItem: MyWorksData) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MyWorksData, newItem: MyWorksData) =
            oldItem.position == newItem.position
    }
) {

    inner class MyWorkViewHolder(private val binding: ItemMyWorkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            if (item.visible.not()) {
                binding.root.visibility = View.GONE
            }
            binding.ivWorkImage.setImageResource(item.image)
            binding.ivWorkImage.setBackgroundResource(item.background)
            binding.tvWorkName.text = item.workName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyWorkViewHolder(
        ItemMyWorkBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: MyWorkViewHolder, position: Int) {
        holder.bind(position)
    }
}