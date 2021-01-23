package com.personalgrowth.repository.mainRepository

import androidx.lifecycle.LiveData
import com.personalgrowth.data.channel.ChannelsLocal
import com.personalgrowth.data.channelCategory.ChannelCategoryLocal
import com.personalgrowth.data.newEpisodes.NewEpisodeLocal
import com.personalgrowth.database.entity.ChannelCategory
import com.personalgrowth.database.entity.Channels
import com.personalgrowth.database.entity.NewEpisodes
import com.personalgrowth.remote.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val channelCategoryLocal: ChannelCategoryLocal,
    private val newEpisodeLocal: NewEpisodeLocal,
    private val channelsLocal: ChannelsLocal,
    private val dispatcher: CoroutineDispatcher
) : MainRepository {

    private val channelCategories: LiveData<ChannelCategory> =
        channelCategoryLocal.getCategories()
    private val newEpisodes: LiveData<NewEpisodes> = newEpisodeLocal.getNewEpisodes()
    private val channels: LiveData<Channels> = channelsLocal.getChannels()

    override fun getCategories(
        channelResponse: (channelCategory: ChannelCategory) -> Unit,
        error: (error: String) -> Unit
    ) {
        networkRepository.channelsCategory({ remoteChannelsCategories ->

            val channelCategory = ChannelCategory(0, remoteChannelsCategories.data)
            channelResponse(channelCategory)

        }, {
            error(it)
        })

    }

    override suspend fun saveCategory(
        channelCategoryResponse: ChannelCategory,
        channelCategoryLiveData: (channelCategory: LiveData<ChannelCategory>) -> Unit
    ) {

        coroutineScope {

            launch {
                if (channelCategories.value == null) {
                    channelCategoryLocal.saveCategories(channelCategoryResponse)
                    channelCategoryLiveData(channelCategoryLocal.getCategories())
                } else {
                    channelCategoryLocal.updateCategories(channelCategoryResponse)
                    channelCategoryLiveData(channelCategoryLocal.getCategories())
                }
            }

        }

    }

    override fun getNewEpisodes(
        newEpisodesResponse: (newEpisode: NewEpisodes) -> Unit,
        error: (error: String) -> Unit
    ) {

        networkRepository.newEpisodes({ remoteNewEpisodes ->
            val newEpisode = NewEpisodes(0, remoteNewEpisodes.data)
            newEpisodesResponse(newEpisode)

        }, {
            error(it)
        })

    }

    override suspend fun saveNewEpisodes(
        newEpisodeResponse: NewEpisodes,
        newEpisodeLiveData: (newEpisode: LiveData<NewEpisodes>) -> Unit
    ) {

        if (newEpisodes.value == null) {
            newEpisodeLocal.saveNewEpisodes(newEpisodeResponse)
            newEpisodeLiveData(newEpisodes)
        } else {
            newEpisodeLocal.updateNewEpisodes(newEpisodeResponse)
            newEpisodeLiveData(newEpisodes)
        }

    }


    override fun getChannels(
        channelResponse: (channel: Channels) -> Unit,
        error: (error: String) -> Unit
    ) {

        networkRepository.channels({ remoteChannels ->

            val channel = Channels(0, remoteChannels.data)
            channelResponse(channel)

        }, {
            error(it)
        })

    }

    override suspend fun saveChannels(
        channelResponse: Channels,
        channelsLiveData: (channelsLiveData: LiveData<Channels>) -> Unit
    ) {

        if (channels.value == null) {
            channelsLocal.saveChannels(channelResponse)
            channelsLiveData(channels)
        } else {
            channelsLocal.updateChannels(channelResponse)
            channelsLiveData(channels)
        }

    }

    override fun getLocalChannelCategories() = channelCategoryLocal.getCategories()

    override fun getLocalNewEpisodes() = newEpisodeLocal.getNewEpisodes()

    override fun getLocalChannels() = channelsLocal.getChannels()

}