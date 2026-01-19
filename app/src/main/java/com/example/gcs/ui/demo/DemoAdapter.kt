package com.example.gcs.ui.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gcs.data.model.DemoItem
import com.example.gcs.databinding.ItemDemoBinding

class DemoAdapter : ListAdapter<DemoItem, DemoAdapter.DemoViewHolder>(DemoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val binding = ItemDemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DemoViewHolder(private val binding: ItemDemoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DemoItem) {
            binding.itemTitle.text = item.title
            binding.itemBody.text = item.body
        }
    }
}

class DemoDiffCallback : DiffUtil.ItemCallback<DemoItem>() {
    override fun areItemsTheSame(oldItem: DemoItem, newItem: DemoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DemoItem, newItem: DemoItem): Boolean {
        return oldItem == newItem
    }
}
