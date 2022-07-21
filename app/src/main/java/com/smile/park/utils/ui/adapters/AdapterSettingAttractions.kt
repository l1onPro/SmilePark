package com.smile.park.utils.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smile.park.databinding.ItemSettingAttractionBinding
import com.smile.park.utils.ui.decorations.SpacingItemDecoration

class AdapterSettingAttractions : ListAdapter<UiAttraction, RecyclerView.ViewHolder>(DiffUtil()) {

    fun attach(target: RecyclerView) {
        target.adapter = this
        target.layoutManager = LinearLayoutManager(target.context, LinearLayoutManager.VERTICAL, false)
        target.addItemDecoration(SpacingItemDecoration(SpacingItemDecoration.Orientation.VERTICAL))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemSettingAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ViewHolder).bind(item)
    }

    class ViewHolder(private val binding: ItemSettingAttractionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiAttraction) {
            binding.itemSettingAttractionTitle.text = item.title
            binding.itemSettingAttractionDescription.text = item.description

            binding.root.setOnClickListener {
                item.onClick.invoke()
            }
            binding.itemSettingAttractionDelete.isVisible = item.onDeleteClick != null
            binding.itemSettingAttractionDelete.setOnClickListener {
                item.onDeleteClick?.invoke()
            }
        }

    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<UiAttraction>() {
        override fun areItemsTheSame(oldItem: UiAttraction, newItem: UiAttraction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiAttraction, newItem: UiAttraction): Boolean {
            return oldItem == newItem
        }

    }
}

data class UiAttraction(
    val id: Int,
    val title: String,
    val description: String,
    val onClick: () -> Unit,
    val onDeleteClick: (() -> Unit)? = null,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UiAttraction

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}