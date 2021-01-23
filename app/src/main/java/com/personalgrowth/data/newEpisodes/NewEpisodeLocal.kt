package com.personalgrowth.data.newEpisodes

import androidx.lifecycle.LiveData
import com.personalgrowth.database.entity.NewEpisodes

interface NewEpisodeLocal {

    suspend fun saveNewEpisodes(newEpisodes: NewEpisodes)

    suspend fun updateNewEpisodes(newEpisodes: NewEpisodes)

    fun getNewEpisodes(): LiveData<NewEpisodes>

}