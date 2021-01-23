package com.personalgrowth.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.personalgrowth.FakeData
import com.personalgrowth.database.entity.ChannelCategory
import com.personalgrowth.database.entity.Channels
import com.personalgrowth.database.entity.NewEpisodes
import com.personalgrowth.repository.mainRepository.MainRepository
import javax.inject.Inject

class FakeMainRepository @Inject constructor() : MainRepository {

    private val channelCategories = MutableLiveData<ChannelCategory>()
    private val newEpisodes = MutableLiveData<NewEpisodes>()
    private val channels = MutableLiveData<Channels>()

    private val channelCategoryLocal =
        mutableListOf(ChannelCategory(0, FakeData.channelCategoryData.data))
    private val newEpisodeLocal = mutableListOf(NewEpisodes(0, FakeData.newEpisodesData.data))
    private val channelLocal = mutableListOf(Channels(0, FakeData.channelData.data))

    companion object {
        var failNetwork = false
    }

    override fun getCategories(
        channelResponse: (channelCategory: ChannelCategory) -> Unit,
        error: (error: String) -> Unit
    ) {

        if (failNetwork) {
            error(FakeData.errorMessage)
        } else {
            val channelCategory = ChannelCategory(0, FakeData.channelCategoryData.data)
            channelResponse(channelCategory)
        }

    }

    override suspend fun saveCategory(
        channelCategoryResponse: ChannelCategory,
        channelCategoryLiveData: (channelCategory: LiveData<ChannelCategory>) -> Unit
    ) {

        if (channelCategories.value == null) {
            channelCategoryLocal.add(channelCategoryResponse)
            channelCategories.value = channelCategoryResponse
            channelCategoryLiveData(channelCategories)
        } else {
            channelCategoryLocal.add(channelCategoryResponse)
            channelCategories.value = channelCategoryResponse
            channelCategoryLiveData(channelCategories)
        }

    }

    override fun getNewEpisodes(
        newEpisodesResponse: (newEpisode: NewEpisodes) -> Unit,
        error: (error: String) -> Unit
    ) {

        if (failNetwork) {
            error(FakeData.errorMessage)
        } else {
            val newEpisode = NewEpisodes(0, FakeData.newEpisodesData.data)
            newEpisodesResponse(newEpisode)
        }

    }

    override suspend fun saveNewEpisodes(
        newEpisodeResponse: NewEpisodes,
        newEpisodeLiveData: (newEpisode: LiveData<NewEpisodes>) -> Unit
    ) {
        if (newEpisodes.value == null) {
            newEpisodeLocal.add(newEpisodeResponse)
            newEpisodes.value = newEpisodeResponse
            newEpisodeLiveData(newEpisodes)
        } else {
            newEpisodeLocal.add(newEpisodeResponse)
            newEpisodes.value = newEpisodeResponse
            newEpisodeLiveData(newEpisodes)
        }

    }

    override fun getChannels(
        channelResponse: (channel: Channels) -> Unit,
        error: (error: String) -> Unit
    ) {

        if (failNetwork) {
            error(FakeData.errorMessage)
        } else {
            val channel = Channels(0, FakeData.channelData.data)
            channelResponse(channel)
        }
    }

    override suspend fun saveChannels(
        channelResponse: Channels,
        channelsLiveData: (channelsLiveData: LiveData<Channels>) -> Unit
    ) {
        if (channels.value == null) {
            channelLocal.add(channelResponse)
            channels.value = channelResponse
            channelsLiveData(channels)
        } else {
            channelLocal.add(channelResponse)
            channels.value = channelResponse
            channelsLiveData(channels)
        }

    }

    override fun getLocalChannelCategories(): LiveData<ChannelCategory> = channelCategories

    override fun getLocalNewEpisodes(): LiveData<NewEpisodes> = newEpisodes

    override fun getLocalChannels(): LiveData<Channels> = channels
}