package com.personalgrowth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.personalgrowth.R
import com.personalgrowth.model.ChannelItem
import kotlinx.android.synthetic.main.channel_course_list.view.*
import kotlinx.android.synthetic.main.channel_series_list.view.*

class ChannelAdapter(
    private val context: Context,
    private var channelList: List<ChannelItem>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val channelCourse = 1
    private val channelSeries = 2

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return if (viewType == channelCourse) {
            CourseViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.channel_course_list,
                    parent,
                    false
                )
            )
        } else {
            SeriesViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.channel_series_list,
                    parent,
                    false
                )
            )
        }

    }

    override fun getItemViewType(position: Int): Int {

        return if (channelList[position].series.isNullOrEmpty()) {
            channelCourse
        } else {
            channelSeries
        }
    }

    override fun getItemCount(): Int {
        return channelList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            channelCourse -> (holder as CourseViewHolder).bind(channelList[position], context)
            channelSeries -> (holder as SeriesViewHolder).bind(channelList[position], context)
        }
    }

    fun setItems(channelItemList: List<ChannelItem>) {
        this.channelList = channelItemList
        notifyDataSetChanged()
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                channelCourseAdapter = ChannelCourseAdapter(context, it)
                channelCourseRecyclerView.adapter = channelCourseAdapter
            }
        }
    }

    class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                channelSeriesAdapter = ChannelSeriesAdapter(context, it)
                channelSeriesRecycler.adapter = channelSeriesAdapter
            }


        }
    }
}