package com.personalgrowth.repository.newEpisodes

import androidx.lifecycle.LiveData
import com.personalgrowth.database.dao.NewEpisodesDAO
import com.personalgrowth.database.entity.NewEpisodes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultNewEpisodeLocal(
    private val newEpisodesDAO: NewEpisodesDAO,
    private val coroutineDispatcher: CoroutineDispatcher
) : NewEpisodeLocal {

    override suspend fun saveNewEpisodes(newEpisodes: NewEpisodes) =
        withContext(coroutineDispatcher) { newEpisodesDAO.saveNewEpisodes(newEpisodes) }

    override suspend fun updateNewEpisodes(newEpisodes: NewEpisodes) =
        withContext(coroutineDispatcher) { newEpisodesDAO.updateNewEpisodes(newEpisodes) }

    override fun getNewEpisodes(): LiveData<NewEpisodes> = newEpisodesDAO.getNewEpisodes()

}