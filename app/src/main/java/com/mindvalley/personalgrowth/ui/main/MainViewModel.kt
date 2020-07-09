package com.mindvalley.personalgrowth.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindvalley.personalgrowth.database.entity.ChannelCategory
import com.mindvalley.personalgrowth.database.entity.Channels
import com.mindvalley.personalgrowth.database.entity.NewEpisodes
import com.mindvalley.personalgrowth.repository.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) :
    ViewModel() {

    var newEpisodes: LiveData<NewEpisodes> = mainRepository.newEpisodes
    var channels: LiveData<Channels> = mainRepository.channels
    var channelCategories: LiveData<ChannelCategory> = mainRepository.channelCategories

    var newEpisodesError: MutableLiveData<String> = MutableLiveData()

    init {
        processNewEpisodes()
        processChannels()
        processCategories()
    }

    fun processNewEpisodes() {

        viewModelScope.launch {
            mainRepository.getNewEpisodes({
                saveNewEpisodes(it, coroutineDispatcher)
            }, {
                newEpisodesError.value = it
            })
        }
    }

    private fun saveNewEpisodes(newEpisode: NewEpisodes, coroutineDispatcher: CoroutineDispatcher) {

        viewModelScope.launch(coroutineDispatcher) {
            mainRepository.saveNewEpisodes(newEpisode) { newEpisode ->
                newEpisodes = newEpisode
            }
        }
    }


    fun processChannels() {

        viewModelScope.launch {
            mainRepository.getChannels({
                saveChannels(it, coroutineDispatcher)
            }, {}
            )
        }
    }

    private fun saveChannels(channel: Channels, coroutineDispatcher: CoroutineDispatcher) {

        viewModelScope.launch(coroutineDispatcher) {
            mainRepository.saveChannels(channel) { channel ->
                channels = channel
            }
        }
    }

    fun processCategories() {

        viewModelScope.launch {
            mainRepository.getCategories({
                saveCategories(it, coroutineDispatcher)

            }, {}
            )
        }
    }

    private fun saveCategories(
        channelCategory: ChannelCategory,
        coroutineDispatcher: CoroutineDispatcher
    ) {

        viewModelScope.launch(coroutineDispatcher) {
            mainRepository.saveCategory(channelCategory) { channelCategory ->
                channelCategories = channelCategory
            }
        }
    }

}