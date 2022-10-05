package com.personalgrowth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.personalgrowth.databinding.CourseItemBinding
import com.personalgrowth.model.ChannelMedia
import kotlin.math.min

class ChannelCourseAdapter :
    ListAdapter<ChannelMedia, RecyclerView.ViewHolder>(
        ChannelMediaDiffCallback()
    ) {

    class ChannelMediaDiffCallback : DiffUtil.ItemCallback<ChannelMedia>() {
        override fun areItemsTheSame(oldItem: ChannelMedia, newItem: ChannelMedia): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ChannelMedia, newItem: ChannelMedia): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {

        return CourseViewHolder(
            CourseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return min(currentList.size, 6)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CourseViewHolder).bind(currentList[position], holder.itemView.context)
    }

    class CourseViewHolder(itemView: CourseItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val channelCourseTitle: TextView = itemView.channelCourseMediaTitleTxt
        private val channelCourseImage: ImageView = itemView.channelCourseMediaPhotoImg
        private val requestOptions =
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(500, 500)

        fun bind(channelMedia: ChannelMedia, context: Context) {
            channelCourseTitle.text = channelMedia.title
            Glide.with(context).load(channelMedia.coverAsset?.url).apply(requestOptions)
                .into(channelCourseImage)

        }
    }
}