package com.personalgrowth.remote

import com.personalgrowth.model.ChannelCategoryResponse
import com.personalgrowth.model.ChannelsResponse
import com.personalgrowth.model.NewEpisodesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("raw/A0CgArX3")
    fun getChannelsCategories(): Call<ChannelCategoryResponse>

    @GET("raw/z5AExTtw")
    fun getNewEpisodes(): Call<NewEpisodesResponse>

    @GET("raw/Xt12uVhM")
    fun getChannels(): Call<ChannelsResponse>

}