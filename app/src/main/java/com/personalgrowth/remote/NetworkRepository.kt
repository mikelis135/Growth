package com.personalgrowth.remote

import com.personalgrowth.model.ChannelCategoryResponse
import com.personalgrowth.model.ChannelsResponse
import com.personalgrowth.model.NewEpisodesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val service: ApiService
) {

    fun channelsCategory(
        onSuccess: (channelCategoryResponse: ChannelCategoryResponse) -> Unit,
        onError: (error: String) -> Unit
    ) {

        service.getChannelsCategories().enqueue(object : Callback<ChannelCategoryResponse> {
            override fun onFailure(call: Call<ChannelCategoryResponse>, t: Throwable) {
                onError("${t.message}")
            }

            override fun onResponse(
                call: Call<ChannelCategoryResponse>,
                response: Response<ChannelCategoryResponse>
            ) {
                response.body()?.let { onSuccess(it) }
            }

        })
    }

    fun newEpisodes(
        onSuccess: (newEpisodesResponse: NewEpisodesResponse) -> Unit,
        onError: (error: String) -> Unit
    ) {

        service.getNewEpisodes().enqueue(object : Callback<NewEpisodesResponse> {
            override fun onFailure(call: Call<NewEpisodesResponse>, t: Throwable) {
                onError("${t.message}")
            }

            override fun onResponse(
                call: Call<NewEpisodesResponse>,
                response: Response<NewEpisodesResponse>
            ) {
                response.body()?.let { onSuccess(it) }
            }

        })
    }

    fun channels(
        onSuccess: (channelsResponse: ChannelsResponse) -> Unit,
        onError: (error: String) -> Unit
    ) {

        service.getChannels().enqueue(object : Callback<ChannelsResponse> {
            override fun onFailure(call: Call<ChannelsResponse>, t: Throwable) {
                onError("${t.message}")
            }

            override fun onResponse(
                call: Call<ChannelsResponse>,
                response: Response<ChannelsResponse>
            ) {
                response.body()?.let { onSuccess(it) }
            }

        })
    }

}

