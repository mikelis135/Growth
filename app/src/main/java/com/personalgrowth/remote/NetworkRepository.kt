package com.personalgrowth.remote

import com.personalgrowth.model.ChannelCategoryResponse
import com.personalgrowth.model.ChannelsResponse
import com.personalgrowth.model.NewEpisodesResponse

interface NetworkRepository {

    fun channelsCategory(
        onSuccess: (channelCategoryResponse: ChannelCategoryResponse) -> Unit,
        onError: (error: String) -> Unit
    )

    fun newEpisodes(
        onSuccess: (newEpisodesResponse: NewEpisodesResponse) -> Unit,
        onError: (error: String) -> Unit
    )

    fun channels(
        onSuccess: (channelsResponse: ChannelsResponse) -> Unit,
        onError: (error: String) -> Unit
    )
}