package com.personalgrowth.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.personalgrowth.databinding.ChannelCategoryItemBinding
import com.personalgrowth.model.CategoryNames

class CategoriesAdapter :
    ListAdapter<CategoryNames, CategoriesAdapter.EpisodeViewHolder>(CategoryNamesItemDiffCallback()) {

    class CategoryNamesItemDiffCallback : DiffUtil.ItemCallback<CategoryNames>() {
        override fun areItemsTheSame(oldItem: CategoryNames, newItem: CategoryNames): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CategoryNames, newItem: CategoryNames): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeViewHolder {

        return EpisodeViewHolder(
            ChannelCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class EpisodeViewHolder(itemView: ChannelCategoryItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val categoryName: TextView = itemView.categoryNameTxt

        fun bind(categoryNames: CategoryNames) {
            categoryName.text = categoryNames.name

        }
    }
}