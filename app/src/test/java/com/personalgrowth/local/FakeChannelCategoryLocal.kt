package com.personalgrowth.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.personalgrowth.FakeData
import com.personalgrowth.database.entity.ChannelCategory
import com.personalgrowth.repository.channelCategory.ChannelCategoryLocal
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class FakeChannelCategoryLocal @Inject constructor(): ChannelCategoryLocal {

    private var channelCategoryLocal = ChannelCategory(0, FakeData.channelCategoryData.data)
    private val channelCategoryLiveData = MutableLiveData<ChannelCategory>()

    override suspend fun saveCategories(channelCategory: ChannelCategory) {

        channelCategoryLocal = channelCategory
        channelCategoryLiveData.value = channelCategory
    }

    override suspend fun updateCategories(channelCategory: ChannelCategory) {
        channelCategoryLocal = channelCategory
        channelCategoryLiveData.value = channelCategory
    }

    override fun getCategories(): LiveData<ChannelCategory> = channelCategoryLiveData
}