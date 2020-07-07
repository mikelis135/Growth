package com.mindvalley.personalgrowth.repository

import androidx.lifecycle.LiveData
import com.mindvalley.personalgrowth.database.dao.ChannelCategoriesDAO
import com.mindvalley.personalgrowth.database.dao.ChannelsDAO
import com.mindvalley.personalgrowth.database.dao.NewEpisodesDAO
import com.mindvalley.personalgrowth.database.entity.ChannelCategory
import com.mindvalley.personalgrowth.database.entity.Channels
import com.mindvalley.personalgrowth.database.entity.NewEpisodes
import com.mindvalley.personalgrowth.remote.NetworkRepository
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val channelCategoriesDAO: ChannelCategoriesDAO,
    private val newEpisodesDAO: NewEpisodesDAO,
    private val channelsDAO: ChannelsDAO
) {

    val channelCategories: LiveData<ChannelCategory> = channelCategoriesDAO.getCategories()
    val newEpisodes: LiveData<NewEpisodes> = newEpisodesDAO.getNewEpisodes()
    val channels: LiveData<Channels> = channelsDAO.getChannels()

    fun getCategories(
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

    suspend fun saveCategory(
        channelCategoryResponse: ChannelCategory,
        channelCategoryLiveData: (channelCategory: LiveData<ChannelCategory>) -> Unit
    ) {

        if (channelCategories.value == null) {
            channelCategoriesDAO.saveCategories(channelCategoryResponse)
            channelCategoryLiveData(channelCategoriesDAO.getCategories())
        } else {
            channelCategoriesDAO.updateCategories(channelCategoryResponse)
            channelCategoryLiveData(channelCategoriesDAO.getCategories())
        }
    }


    fun getNewEpisodes(
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

    suspend fun saveNewEpisodes(
        newEpisodeResponse: NewEpisodes,
        newEpisodeLiveData: (newEpisode: LiveData<NewEpisodes>) -> Unit
    ) {

        if (newEpisodes.value == null) {
            newEpisodesDAO.saveNewEpisodes(newEpisodeResponse)
            newEpisodeLiveData(newEpisodes)
        } else {
            newEpisodesDAO.updateNewEpisodes(newEpisodeResponse)
            newEpisodeLiveData(newEpisodes)
        }

    }


    fun getChannels(
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

    suspend fun saveChannels(
        channelResponse: Channels,
        channelsLiveData: (channelsLiveData: LiveData<Channels>) -> Unit
    ) {

        if (channels.value == null) {
            channelsDAO.saveChannels(channelResponse)
            channelsLiveData(channels)
        } else {
            channelsDAO.updateChannels(channelResponse)
            channelsLiveData(channels)
        }

    }

}