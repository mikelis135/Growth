package com.mindvalley.personalgrowth.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mindvalley.personalgrowth.database.entity.Channels

@Dao
interface ChannelsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChannels(channels: Channels)

    @Update
    suspend fun updateChannels(channels: Channels)

    @Query("select * from channelsTable")
    fun getChannels(): LiveData<Channels>

}