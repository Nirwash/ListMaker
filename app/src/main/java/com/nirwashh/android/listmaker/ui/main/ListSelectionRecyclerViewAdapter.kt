package com.nirwashh.android.listmaker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nirwashh.android.listmaker.TaskList
import com.nirwashh.android.listmaker.databinding.ListSelectionViewHolderBinding

class ListSelectionRecyclerViewAdapter(private val lists: MutableList<TaskList>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val b = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSelectionViewHolder(b)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.b.itemNumber.text = (position + 1).toString()
        holder.b.itemString.text = lists[position].name
    }

    override fun getItemCount() = lists.size

    fun listsUpdated() {
        notifyItemInserted(lists.size - 1)
    }
}