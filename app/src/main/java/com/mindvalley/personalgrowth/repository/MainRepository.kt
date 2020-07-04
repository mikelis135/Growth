package com.mindvalley.personalgrowth.repository

import androidx.lifecycle.LiveData
import com.mindvalley.personalgrowth.local.dao.ChannelCategoriesDAO
import com.mindvalley.personalgrowth.local.dao.ChannelsDAO
import com.mindvalley.personalgrowth.local.dao.NewEpisodesDAO
import com.mindvalley.personalgrowth.local.entity.ChannelCategory
import com.mindvalley.personalgrowth.local.entity.Channels
import com.mindvalley.personalgrowth.local.entity.NewEpisodes
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val channelCategoriesDAO: ChannelCategoriesDAO,
    private val newEpisodesDAO: NewEpisodesDAO,
    private val channelsDAO: ChannelsDAO
) {

    val channelCategories: LiveData<ChannelCategory> = channelCategoriesDAO.getCategories()
    val newEpisodes: LiveData<NewEpisodes> = newEpisodesDAO.getNewEpisodes()
    val channels: LiveData<Channels> = channelsDAO.getChannels()

    suspend fun saveCategories(channelCategory: ChannelCategory) {

        if (channelCategories.value == null) {
            channelCategoriesDAO.saveCategories(channelCategory)
        } else {
            channelCategoriesDAO.updateCategories(channelCategory)
        }

    }

    suspend fun saveNewEpisodes(newEpisode: NewEpisodes) {

        if (newEpisodes.value == null) {
            newEpisodesDAO.saveNewEpisodes(newEpisode)
        } else {
            newEpisodesDAO.updateNewEpisodes(newEpisode)
        }

    }

    suspend fun saveChannels(channel: Channels) {

        if (channels.value == null) {
            channelsDAO.saveChannels(channel)
        } else {
            channelsDAO.updateChannels(channel)
        }

    }

}