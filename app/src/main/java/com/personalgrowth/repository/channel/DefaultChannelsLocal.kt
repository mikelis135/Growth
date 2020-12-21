package com.personalgrowth.repository.channel

import androidx.lifecycle.LiveData
import com.personalgrowth.database.dao.ChannelsDAO
import com.personalgrowth.database.entity.Channels
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultChannelsLocal(
    private val channelsDAO: ChannelsDAO,
    private val coroutineDispatcher: CoroutineDispatcher
) : ChannelsLocal {

    override suspend fun saveChannels(channels: Channels) =
        withContext(coroutineDispatcher) { channelsDAO.saveChannels(channels) }

    override suspend fun updateChannels(channels: Channels) =
        withContext(coroutineDispatcher) { channelsDAO.updateChannels(channels) }

    override fun getChannels(): LiveData<Channels> = channelsDAO.getChannels()

}