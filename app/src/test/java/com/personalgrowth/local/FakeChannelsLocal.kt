package com.personalgrowth.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.personalgrowth.FakeData
import com.personalgrowth.database.entity.Channels
import com.personalgrowth.repository.channel.ChannelsLocal
import javax.inject.Inject

class FakeChannelsLocal @Inject constructor(): ChannelsLocal {

    private var channelLocal = Channels(0, FakeData.channelData.data)
    private val channelsLiveData = MutableLiveData<Channels>()

    override suspend fun saveChannels(channels: Channels) {
        channelLocal = channels
        channelsLiveData.value = channels
    }

    override suspend fun updateChannels(channels: Channels) {
        channelLocal = channels
        channelsLiveData.value = channels
    }

    override fun getChannels(): LiveData<Channels> = channelsLiveData
}