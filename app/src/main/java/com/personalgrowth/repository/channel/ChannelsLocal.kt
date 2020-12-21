package com.personalgrowth.repository.channel

import androidx.lifecycle.LiveData
import com.personalgrowth.database.entity.Channels

interface ChannelsLocal {

    suspend fun saveChannels(channels: Channels)

    suspend fun updateChannels(channels: Channels)

    fun getChannels(): LiveData<Channels>

}