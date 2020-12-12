package com.personalgrowth.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.personalgrowth.database.entity.ChannelCategory

@Dao

interface ChannelCategoriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(channelCategory: ChannelCategory)

    @Update
    suspend fun updateCategories(channelCategory: ChannelCategory)

    @Query("select * from channelCategoryTable")
    fun getCategories(): LiveData<ChannelCategory>

}