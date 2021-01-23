package com.personalgrowth.data.channelCategory

import androidx.lifecycle.LiveData
import com.personalgrowth.database.dao.ChannelCategoriesDAO
import com.personalgrowth.database.entity.ChannelCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultChannelCategoryLocal(
    private val channelCategoriesDAO: ChannelCategoriesDAO,
    private val coroutineDispatcher: CoroutineDispatcher
) :
    ChannelCategoryLocal {

    override suspend fun saveCategories(channelCategory: ChannelCategory) =
        withContext(coroutineDispatcher) { channelCategoriesDAO.saveCategories(channelCategory) }

    override suspend fun updateCategories(channelCategory: ChannelCategory) =
        withContext(coroutineDispatcher) { channelCategoriesDAO.updateCategories(channelCategory) }

    override fun getCategories(): LiveData<ChannelCategory> = channelCategoriesDAO.getCategories()
}