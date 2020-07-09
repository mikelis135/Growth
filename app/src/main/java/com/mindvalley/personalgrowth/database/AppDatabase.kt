package com.mindvalley.personalgrowth.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mindvalley.personalgrowth.database.converter.AllChannelsConverter
import com.mindvalley.personalgrowth.database.converter.CategoryConverter
import com.mindvalley.personalgrowth.database.converter.MediaConverter
import com.mindvalley.personalgrowth.database.dao.ChannelCategoriesDAO
import com.mindvalley.personalgrowth.database.dao.ChannelsDAO
import com.mindvalley.personalgrowth.database.dao.NewEpisodesDAO
import com.mindvalley.personalgrowth.database.entity.ChannelCategory
import com.mindvalley.personalgrowth.database.entity.Channels
import com.mindvalley.personalgrowth.database.entity.NewEpisodes

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