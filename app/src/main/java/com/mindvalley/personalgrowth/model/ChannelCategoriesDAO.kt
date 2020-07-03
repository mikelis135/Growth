package com.mindvalley.personalgrowth.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChannelCategoriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(channelCategory: ChannelCategory)

    @Update
    suspend fun updateCategories(channelCategory: ChannelCategory)

    @Query("select * from category_table")
    fun getCategories() : LiveData<ChannelCategory>

}