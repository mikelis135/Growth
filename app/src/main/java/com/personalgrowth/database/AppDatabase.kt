package com.personalgrowth.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.personalgrowth.database.converter.AllChannelsConverter
import com.personalgrowth.database.converter.CategoryConverter
import com.personalgrowth.database.converter.MediaConverter
import com.personalgrowth.database.dao.ChannelCategoriesDAO
import com.personalgrowth.database.dao.ChannelsDAO
import com.personalgrowth.database.dao.NewEpisodesDAO
import com.personalgrowth.database.entity.ChannelCategory
import com.personalgrowth.database.entity.Channels
import com.personalgrowth.database.entity.NewEpisodes

@Database(
    entities = [ChannelCategory::class, NewEpisodes::class, Channels::class],
    version = 9,
    exportSchema = false
)
@TypeConverters(
    CategoryConverter::class,
    MediaConverter::class,
    AllChannelsConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun channelsDao(): ChannelsDAO
    abstract fun newEpisodesDao(): NewEpisodesDAO
    abstract fun channelCategoriesDao(): ChannelCategoriesDAO

}