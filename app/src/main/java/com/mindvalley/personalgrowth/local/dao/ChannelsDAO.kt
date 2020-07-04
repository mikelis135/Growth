package com.mindvalley.personalgrowth.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mindvalley.personalgrowth.local.entity.Channels

@Dao
interface ChannelsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChannels(channels: Channels)

    @Update
    suspend fun updateChannels(channels: Channels)

    @Query("select * from channels_table")
    fun getChannels(): LiveData<Channels>

}