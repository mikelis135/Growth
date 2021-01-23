package com.personalgrowth.repository.mainRepository

import androidx.lifecycle.LiveData
import com.personalgrowth.database.entity.ChannelCategory
import com.personalgrowth.database.entity.Channels
import com.personalgrowth.database.entity.NewEpisodes

interface MainRepository {

    fun getCategories(
        channelResponse: (channelCategory: ChannelCategory) -> Unit,
        error: (error: String) -> Unit
    )

    suspend fun saveCategory(
        channelCategoryResponse: ChannelCategory,
        channelCategoryLiveData: (channelCategory: LiveData<ChannelCategory>) -> Unit
    )

    fun getNewEpisodes(
        newEpisodesResponse: (newEpisode: NewEpisodes) -> Unit,
        error: (error: String) -> Unit
    )

    suspend fun saveNewEpisodes(
        newEpisodeResponse: NewEpisodes,
        newEpisodeLiveData: (newEpisode: LiveData<NewEpisodes>) -> Unit
    )

    fun getChannels(
        channelResponse: (channel: Channels) -> Unit,
        error: (error: String) -> Unit
    )

    suspend fun saveChannels(
        channelResponse: Channels,
        channelsLiveData: (channelsLiveData: LiveData<Channels>) -> Unit
    )

    fun getLocalChannelCategories(): LiveData<ChannelCategory>

    fun getLocalNewEpisodes(): LiveData<NewEpisodes>

    fun getLocalChannels(): LiveData<Channels>

}