package com.personalgrowth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.personalgrowth.R
import com.personalgrowth.databinding.ChannelCourseListBinding
import com.personalgrowth.databinding.ChannelSeriesListBinding
import com.personalgrowth.model.ChannelItem

class ChannelAdapter :
    ListAdapter<ChannelItem, RecyclerView.ViewHolder>(ChannelItemDiffCallback()) {

    private val channelCourse = 1
    private val channelSeries = 2

    class ChannelItemDiffCallback : DiffUtil.ItemCallback<ChannelItem>() {
        override fun areItemsTheSame(oldItem: ChannelItem, newItem: ChannelItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ChannelItem, newItem: ChannelItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return if (viewType == channelCourse) {
            CourseViewHolder(
                ChannelCourseListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            SeriesViewHolder(
                ChannelSeriesListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun getItemViewType(position: Int): Int {

        return if (currentList[position].series.isNullOrEmpty()) {
            channelCourse
        } else {
            channelSeries
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            channelCourse -> (holder as CourseViewHolder).bind(
                currentList[position],
                holder.itemView.context
            )
            channelSeries -> (holder as SeriesViewHolder).bind(
                currentList[position],
                holder.itemView.context
            )
        }
    }

    class CourseViewHolder(itemView: ChannelCourseListBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val channelTitle: TextView = itemView.channelCourseItemTxt
        private val channelCountTitle: TextView = itemView.channelCourseItemCountTxt
        private val channelCourseImage: ImageView = itemView.channelCoursePhotoImg
        private val channelCourseRecyclerView: RecyclerView = itemView.channelCourseRecycler

        private lateinit var channelCourseAdapter: ChannelCourseAdapter

        fun bind(channelItem: ChannelItem, context: Context) {
            channelTitle.text = channelItem.title

            channelItem.latestMedia?.size?.let {
                channelCountTitle.text =
                    context.resources.getQuantityString(R.plurals.episodes, it, it)
            }

            Glide.with(context).load(channelItem.iconAsset?.thumbnailUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder).into(channelCourseImage)

            channelCourseRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            channelItem.latestMedia?.let {
                channelCourseAdapter = ChannelCourseAdapter()
                channelCourseRecyclerView.adapter = channelCourseAdapter
                channelCourseAdapter.submitList(it)
            }
        }
    }

    class SeriesViewHolder(itemView: ChannelSeriesListBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val channelTitle: TextView = itemView.channelSeriesItemTxt
        private val channelCountTitle: TextView = itemView.channelItemCountTxt
        private val channelSeriesImage: ImageView = itemView.channelSeriesPhotoImg
        private val channelSeriesRecycler: RecyclerView = itemView.channelSeriesRecycler

        private lateinit var channelSeriesAdapter: ChannelSeriesAdapter

        fun bind(channelItem: ChannelItem, context: Context) {
            channelTitle.text = channelItem.title
            channelItem.series?.size?.let {
                channelCountTitle.text =
                    context.resources.getQuantityString(R.plurals.series, it, it)
            }

            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(context).load(channelItem.iconAsset?.thumbnailUrl).apply(requestOptions)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder).into(channelSeriesImage)

            channelSeriesRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            channelItem.series?.let {
                channelSeriesAdapter = ChannelSeriesAdapter()
                channelSeriesRecycler.adapter = channelSeriesAdapter
            }


        }
    }
}