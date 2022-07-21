package com.smile.park.utils.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smile.park.databinding.ItemStatisticMainBinding
import com.smile.park.utils.ui.decorations.SpacingItemDecoration

class AdapterStatistic : ListAdapter<StateList, RecyclerView.ViewHolder>(DiffUtil()) {

    fun attach(target: RecyclerView) {
        target.adapter = this
        target.layoutManager = LinearLayoutManager(target.context, LinearLayoutManager.VERTICAL, false)
        target.addItemDecoration(SpacingItemDecoration(SpacingItemDecoration.Orientation.VERTICAL))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemStatisticMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            StateList.TYPE_MAIN -> {
                (holder as ViewHolder).bind(getItem(position) as UiStatsMain)
            }
            StateList.TYPE_DETAILS -> {
                (holder as ViewHolder).bind(getItem(position) as UiStatsDetails)
            }
        }
    }

    class ViewHolder(private val binding: ItemStatisticMainBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiStatsMain) {
            binding.mainStatsAttractionTitle.text = item.title
            binding.mainStatsAttractionDescription.text = item.description
            binding.mainStatsCountLike.text = item.countLikes.toString()
            binding.mainStatsCountDislike.text = item.countDislikes.toString()
            binding.root.setOnClickListener {
                item.onClick.invoke()
            }
        }

        fun bind(item: UiStatsDetails) {
            binding.mainStatsAttractionTitle.text = item.date
            binding.mainStatsCountLike.text = item.countLikes.toString()
            binding.mainStatsCountDislike.text = item.countDislikes.toString()
        }

    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<StateList>() {
        override fun areItemsTheSame(oldItem: StateList, newItem: StateList): Boolean {
            if (oldItem.type != newItem.type) return false

            when (oldItem.type) {
                StateList.TYPE_MAIN -> {
                    return (oldItem as UiStatsMain).id == (newItem as UiStatsMain).id
                }
                StateList.TYPE_DETAILS -> {
                    return (oldItem as UiStatsDetails).date.hashCode() == (newItem as UiStatsDetails).date.hashCode()
                }
            }

            return false

        }

        override fun areContentsTheSame(oldItem: StateList, newItem: StateList): Boolean {
            if (oldItem.type != newItem.type) return false

            return when (oldItem.type) {
                StateList.TYPE_MAIN -> {
                    return (oldItem as UiStatsMain) == (newItem as UiStatsMain)
                }
                StateList.TYPE_DETAILS -> {
                    return (oldItem as UiStatsDetails) == (newItem as UiStatsDetails)
                }
                else -> false
            }
        }

    }
}

abstract class StateList {

    abstract val type: Int

    companion object {

        const val TYPE_MAIN = 0
        const val TYPE_DETAILS = 1
    }
}

data class UiStatsMain(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val countLikes: Int,
    val countDislikes: Int,
    val onClick: () -> Unit,
) : StateList() {

    override val type: Int
        get() = TYPE_MAIN

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UiStatsMain

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (countLikes != other.countLikes) return false
        if (countDislikes != other.countDislikes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + countLikes
        result = 31 * result + countDislikes
        return result
    }
}

data class UiStatsDetails(
    val date: String,
    val countLikes: Int,
    val countDislikes: Int,
) : StateList() {

    override val type: Int
        get() = TYPE_DETAILS
}