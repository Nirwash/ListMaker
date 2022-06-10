package com.nirwashh.android.listmaker.ui.detail.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nirwashh.android.listmaker.TaskList
import com.nirwashh.android.listmaker.databinding.ListItemViewHolderBinding

class ListItemRecyclerViewAdapter(var list: TaskList) : RecyclerView.Adapter<ListItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val b = ListItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(b)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.b.tvTask.text = list.tasks[position]
    }

    override fun getItemCount() = list.tasks.size
}