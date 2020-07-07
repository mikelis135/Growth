package com.mindvalley.personalgrowth.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindvalley.personalgrowth.database.entity.ChannelCategory
import com.mindvalley.personalgrowth.database.entity.Channels
import com.mindvalley.personalgrowth.database.entity.NewEpisodes
import com.mindvalley.personalgrowth.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) :
    ViewModel() {

    var channelCategories: LiveData<ChannelCategory> = mainRepository.channelCategories
    var newEpisodes: LiveData<NewEpisodes> = mainRepository.newEpisodes
    var channels: LiveData<Channels> = mainRepository.channels

    init {
        processNewEpisodes()
        processChannels()
        processCategories()
    }

    fun processCategories() {

        viewModelScope.launch{

            mainRepository.getCategories({

                viewModelScope.launch{

                    mainRepository.saveCategory(it) { channelCategory ->
                        channelCategories = channelCategory
                    }
                }

            }, {

            })

        }

    }


    fun processNewEpisodes() {

        viewModelScope.launch(Dispatchers.IO) {

            mainRepository.getNewEpisodes({

                viewModelScope.launch(Dispatchers.IO) {
                    mainRepository.saveNewEpisodes(it) { newEpisode ->
                        newEpisodes = newEpisode
                    }
                }
            }, {

            })

        }

    }

    fun processChannels() {
        viewModelScope.launch(Dispatchers.IO) {

            mainRepository.getChannels({

                viewModelScope.launch(Dispatchers.IO) {
                    mainRepository.saveChannels(it) { channel ->
                        channels = channel
                    }
                }
            }, {

            })

        }

    }

}