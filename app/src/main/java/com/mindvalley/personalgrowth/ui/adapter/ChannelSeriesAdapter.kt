package com.mindvalley.personalgrowth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mindvalley.personalgrowth.R
import com.mindvalley.personalgrowth.model.Series
import kotlinx.android.synthetic.main.channel_series_item.view.*

class ChannelSeriesAdapter(
    private val context: Context,
    private val channelSeriesList: List<Series>
) :
    RecyclerView.Adapter<ChannelSeriesAdapter.SeriesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesViewHolder {

        return SeriesViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.channel_series_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return channelSeriesList.size.coerceAtMost(6)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(channelSeriesList[position], context)
    }

    class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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