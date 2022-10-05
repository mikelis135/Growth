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
import com.personalgrowth.databinding.SeriesItemBinding
import com.personalgrowth.model.Series
import kotlin.math.min

class ChannelSeriesAdapter :
    ListAdapter<Series, RecyclerView.ViewHolder>(SeriesDiffCallback()) {

    class SeriesDiffCallback : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesViewHolder {

        return SeriesViewHolder(
            SeriesItemBinding.inflate(
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
        (holder as SeriesViewHolder).bind(currentList[position], holder.itemView.context)
    }

    class SeriesViewHolder(itemView: SeriesItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val channelSeriesTitle: TextView = itemView.channelSeriesMediaTitleTxt
        private val channelSeriesImage: ImageView = itemView.channelSeriesMediaPhotoImg

        private val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        fun bind(series: Series, context: Context) {
            channelSeriesTitle.text = series.title
            Glide.with(context).load(series.coverAsset?.url).apply(requestOptions)
                .into(channelSeriesImage)

        }
    }
}