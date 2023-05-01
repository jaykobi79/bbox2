package com.sgtsoft.bbox2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sgtsoft.bbox2.data.model.M3uItem
import com.sgtsoft.bbox2.databinding.ItemChannelBinding

class ChannelAdapter :
    ListAdapter<M3uItem, ChannelAdapter.ChannelViewHolder>(ChannelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val binding = ItemChannelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChannelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ChannelViewHolder(private val binding: ItemChannelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(channel: M3uItem) {
            binding.channelName.text = channel.channelName
            // Set other channel properties like tvgLogo, etc.
        }
    }
}

class ChannelDiffCallback : DiffUtil.ItemCallback<M3uItem>() {
    override fun areItemsTheSame(oldItem: M3uItem, newItem: M3uItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: M3uItem, newItem: M3uItem): Boolean {
        return oldItem == newItem
    }
}
