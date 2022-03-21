package com.task.demo.utils

import androidx.recyclerview.widget.ListUpdateCallback
import com.task.demo.ui.main.adapter.MainAdapter

class MyCallback : ListUpdateCallback {
    var firstInsert = -1
    private var adapter: MainAdapter? = null
    fun bind(adapter: MainAdapter) {
        this.adapter = adapter
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        adapter?.notifyItemRangeChanged(position, count, payload)
    }

    override fun onInserted(position: Int, count: Int) {
        if (firstInsert == -1 || firstInsert > position) {
            firstInsert = position
        }
        adapter?.notifyItemRangeInserted(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        adapter?.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRemoved(position: Int, count: Int) {
        adapter?.notifyItemRangeRemoved(position, count)
    }
}