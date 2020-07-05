package com.mindvalley.personalgrowth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.personalgrowth.R
import com.mindvalley.personalgrowth.local.entity.CategoryNames
import kotlinx.android.synthetic.main.channel_category_item.view.*

class CategoriesAdapter(
    private val context: Context,
    private val categoryList: List<CategoryNames>
) :
    RecyclerView.Adapter<CategoriesAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeViewHolder {

        return EpisodeViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.channel_category_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryName: TextView = itemView.categoryNameTxt

        fun bind(categoryNames: CategoryNames) {
            categoryName.text = categoryNames.name

        }
    }
}