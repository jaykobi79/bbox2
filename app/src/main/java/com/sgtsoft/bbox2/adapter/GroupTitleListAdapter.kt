package com.sgtsoft.bbox2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sgtsoft.bbox2.R

class GroupTitleListAdapter(
    var groupTitles: List<String>,
    private val onGroupTitleClick: (String) -> Unit
) : RecyclerView.Adapter<GroupTitleListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupTitleTextView: TextView = itemView.findViewById(R.id.groupTitlesTextView)

        init {
            itemView.setOnClickListener {
                onGroupTitleClick(groupTitles[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.group_title_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.groupTitleTextView.text = groupTitles[position]
    }

    override fun getItemCount(): Int {
        return groupTitles.size
    }
}

