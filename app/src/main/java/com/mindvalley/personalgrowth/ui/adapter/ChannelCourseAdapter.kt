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
import com.mindvalley.personalgrowth.local.entity.ChannelMedia
import kotlinx.android.synthetic.main.channel_course_item.view.*

class ChannelCourseAdapter(
    private val context: Context,
    private val channelMediaList: List<ChannelMedia>
) :
    RecyclerView.Adapter<ChannelCourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {

        return CourseViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.channel_course_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return channelMediaList.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(channelMediaList[position], context)
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val channelCourseTitle: TextView = itemView.channelCourseMediaTitleTxt
        private val channelCourseImage: ImageView = itemView.channelCourseMediaPhotoImg
        private val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(500, 500)

        fun bind(channelMedia: ChannelMedia, context: Context) {
            channelCourseTitle.text = channelMedia.title
            Glide.with(context).load(channelMedia.coverAsset?.url).apply(requestOptions).into(channelCourseImage)

        }
    }
}