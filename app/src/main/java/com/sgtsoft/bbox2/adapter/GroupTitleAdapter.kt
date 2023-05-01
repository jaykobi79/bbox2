package com.sgtsoft.bbox2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sgtsoft.bbox2.databinding.ItemGroupTitleBinding

class GroupTitleAdapter(private val onGroupTitleClick: (String) -> Unit) :
    ListAdapter<String, GroupTitleAdapter.GroupTitleViewHolder>(GroupTitleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupTitleViewHolder {
        val binding = ItemGroupTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GroupTitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupTitleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GroupTitleViewHolder(private val binding: ItemGroupTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onGroupTitleClick(binding.groupTitleName.text.toString())
            }
        }

        fun bind(groupTitle: String) {
            binding.groupTitleName.text = groupTitle
        }
    }
}

class GroupTitleDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}