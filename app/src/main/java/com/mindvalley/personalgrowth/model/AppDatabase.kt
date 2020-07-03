package com.mindvalley.personalgrowth.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ChannelCategory::class], version = 4, exportSchema = false)
@TypeConverters(CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun channelsDao(): ChannelsDAO
    abstract fun newEpisodesDao(): NewEpisodesDAO
    abstract fun channelCategoriesDao(): ChannelCategoriesDAO

}