package com.mindvalley.personalgrowth.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface ChannelsDAO {

    @Insert
    fun saveChannels() {

    }

    @Update
    fun updateChannels() {

    }

}