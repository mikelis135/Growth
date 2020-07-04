package com.mindvalley.personalgrowth.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mindvalley.personalgrowth.local.entity.NewEpisodes

@Dao
interface NewEpisodesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewEpisodes(newEpisodes: NewEpisodes)

    @Update
    suspend fun updateNewEpisodes(newEpisodes: NewEpisodes)

    @Query("select * from new_episodes_table")
    fun getNewEpisodes(): LiveData<NewEpisodes>

}