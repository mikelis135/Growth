package com.personalgrowth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.personalgrowth.R
import com.personalgrowth.model.Course
import kotlinx.android.synthetic.main.new_episodes_item.view.*
import kotlin.math.min

class NewEpisodesAdapter(
    private val context: Context
) :
    ListAdapter<Course, NewEpisodesAdapter.EpisodeViewHolder>(CourseDiffCallback()) {

    class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeViewHolder {

        return EpisodeViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.new_episodes_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return min(currentList.size, 6)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(currentList[position], context)
    }

    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val channelTitle: TextView = itemView.channelTitleTxt
        private val courseTitle: TextView = itemView.courseTitleTxt
        private val episodeImage: ImageView = itemView.episodePhotoImg


        fun bind(course: Course, context: Context) {
            channelTitle.text = course.channel?.title
            courseTitle.text = course.title
            Glide.with(context).load(course.coverAsset?.url)
                .apply(RequestOptions().override(500, 500))
                .into(episodeImage)
        }
    }


}