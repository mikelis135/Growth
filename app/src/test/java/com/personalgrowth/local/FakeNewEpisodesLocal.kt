package com.personalgrowth.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.personalgrowth.FakeData
import com.personalgrowth.database.entity.NewEpisodes
import com.personalgrowth.repository.newEpisodes.NewEpisodeLocal
import javax.inject.Inject

class FakeNewEpisodesLocal @Inject constructor() : NewEpisodeLocal {

    private var newEpisodesLocal = NewEpisodes(0, FakeData.newEpisodesData.data)
    private val newEpisodesLiveData = MutableLiveData<NewEpisodes>()

    override suspend fun saveNewEpisodes(newEpisodes: NewEpisodes) {
        newEpisodesLocal = newEpisodes
        newEpisodesLiveData.value = newEpisodes
    }

    override suspend fun updateNewEpisodes(newEpisodes: NewEpisodes) {
        newEpisodesLocal = newEpisodes
        newEpisodesLiveData.value = newEpisodes
    }

    override fun getNewEpisodes(): LiveData<NewEpisodes> = newEpisodesLiveData
}