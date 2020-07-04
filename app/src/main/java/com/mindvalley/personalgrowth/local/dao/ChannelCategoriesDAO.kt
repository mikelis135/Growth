package com.mindvalley.personalgrowth.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mindvalley.personalgrowth.local.entity.ChannelCategory

@Dao
interface ChannelCategoriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(channelCategory: ChannelCategory)

    @Update
    suspend fun updateCategories(channelCategory: ChannelCategory)

    @Query("select * from category_table")
    fun getCategories() : LiveData<ChannelCategory>

}