package com.personalgrowth.remote

import com.personalgrowth.FakeData
import com.personalgrowth.model.ChannelCategoryResponse
import com.personalgrowth.model.ChannelsResponse
import com.personalgrowth.model.NewEpisodesResponse
import javax.inject.Inject

class FakeNetworkRepository @Inject constructor() : NetworkRepository {

    companion object {
        var failNetwork = false
    }

    override fun channelsCategory(
        onSuccess: (channelCategoryResponse: ChannelCategoryResponse) -> Unit,
        onError: (error: String) -> Unit
    ) {
        if (failNetwork) {
            onError("network error")
        } else {
            onSuccess(FakeData.channelCategoryData)
        }
    }

    override fun newEpisodes(
        onSuccess: (newEpisodesResponse: NewEpisodesResponse) -> Unit,
        onError: (error: String) -> Unit
    ) {
        if (failNetwork) {
            onError("network error")
        } else {
            onSuccess(FakeData.newEpisodesData)
        }
    }

    override fun channels(
        onSuccess: (channelsResponse: ChannelsResponse) -> Unit,
        onError: (error: String) -> Unit
    ) {
        if (failNetwork) {
            onError("network error")
        } else {
            onSuccess(FakeData.channelData)
        }
    }
}