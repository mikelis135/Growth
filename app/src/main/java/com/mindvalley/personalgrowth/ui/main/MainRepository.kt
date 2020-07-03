package com.mindvalley.personalgrowth.ui.main

import androidx.lifecycle.LiveData
import com.mindvalley.personalgrowth.model.ChannelCategoriesDAO
import com.mindvalley.personalgrowth.model.ChannelCategory
import javax.inject.Inject

class MainRepository @Inject constructor(private val channelCategoriesDAO: ChannelCategoriesDAO) {

    val channelCategories: LiveData<ChannelCategory> = channelCategoriesDAO.getCategories()

    suspend fun saveCategories(channelCategory: ChannelCategory) {

        if (channelCategories.value == null) {
            channelCategoriesDAO.saveCategories(channelCategory)
        } else {
            channelCategoriesDAO.updateCategories(channelCategory)
        }

    }

}