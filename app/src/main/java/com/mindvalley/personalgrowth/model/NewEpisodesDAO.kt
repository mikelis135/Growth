package com.mindvalley.personalgrowth.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface NewEpisodesDAO {

    @Insert
    fun saveNewEpisodes() {

    }

    @Update
    fun updateNewEpisodes() {

    }

}