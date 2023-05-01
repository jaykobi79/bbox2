package com.sgtsoft.bbox2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sgtsoft.bbox2.R

class ChannelListAdapter(private var channels: List<String>) : RecyclerView.Adapter<ChannelListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.channel_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.channelTextView.text = channels[position]
    }

    override fun getItemCount(): Int = channels.size

    fun updateData(newChannels: List<String>) {
        channels = newChannels
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val channelTextView: TextView = view.findViewById(R.id.channelTextView)
    }
}

