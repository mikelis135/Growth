package com.mindvalley.personalgrowth.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mindvalley.personalgrowth.local.converter.*
import com.mindvalley.personalgrowth.local.dao.ChannelCategoriesDAO
import com.mindvalley.personalgrowth.local.dao.ChannelsDAO
import com.mindvalley.personalgrowth.local.dao.NewEpisodesDAO
import com.mindvalley.personalgrowth.local.entity.ChannelCategory
import com.mindvalley.personalgrowth.local.entity.Channels
import com.mindvalley.personalgrowth.local.entity.NewEpisodes

@Database(entities = [ChannelCategory::class, NewEpisodes::class, Channels::class], version = 8, exportSchema = false)
@TypeConverters(CategoryConverter::class, CourseConverter::class, MediaConverter::class, ChannelItemConverter::class, AllChannelsConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun channelsDao(): ChannelsDAO
    abstract fun newEpisodesDao(): NewEpisodesDAO
    abstract fun channelCategoriesDao(): ChannelCategoriesDAO

}