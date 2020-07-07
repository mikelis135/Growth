package com.mindvalley.personalgrowth.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mindvalley.personalgrowth.database.entity.NewEpisodes

@Dao
interface NewEpisodesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewEpisodes(newEpisodes: NewEpisodes)

    @Update
    suspend fun updateNewEpisodes(newEpisodes: NewEpisodes)

    @Query("select * from newEpisodesTable")
    fun getNewEpisodes(): LiveData<NewEpisodes>

}