package com.mindvalley.personalgrowth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mindvalley.personalgrowth.R
import com.mindvalley.personalgrowth.local.entity.Course
import kotlinx.android.synthetic.main.new_episodes_item.view.*

class NewEpisodesAdapter(
    private val context: Context,
    private val episodeList: List<Course>
) :
    RecyclerView.Adapter<NewEpisodesAdapter.EpisodeViewHolder>() {

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
        return episodeList.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodeList[position], context)
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