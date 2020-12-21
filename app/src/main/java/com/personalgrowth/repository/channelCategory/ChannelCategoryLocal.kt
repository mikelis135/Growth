package com.personalgrowth.repository.channelCategory

import androidx.lifecycle.LiveData
import com.personalgrowth.database.entity.ChannelCategory

interface ChannelCategoryLocal {

    suspend fun saveCategories(channelCategory: ChannelCategory)

    suspend fun updateCategories(channelCategory: ChannelCategory)

    fun getCategories(): LiveData<ChannelCategory>

}