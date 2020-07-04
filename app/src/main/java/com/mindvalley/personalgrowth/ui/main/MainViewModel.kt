package com.mindvalley.personalgrowth.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindvalley.personalgrowth.local.entity.ChannelCategory
import com.mindvalley.personalgrowth.local.entity.Channels
import com.mindvalley.personalgrowth.local.entity.NewEpisodes
import com.mindvalley.personalgrowth.remote.NetworkRepository
import com.mindvalley.personalgrowth.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkRepository: NetworkRepository
) :
    ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    var channelCategories: LiveData<ChannelCategory> = mainRepository.channelCategories
    var newEpisodes: LiveData<NewEpisodes> = mainRepository.newEpisodes
    var channels: LiveData<Channels> = mainRepository.channels

    init {
//        saveCategories()
//        saveNewEpisodes()
        saveChannels()
    }

    private fun saveCategories() {

        viewModelScope.launch(Dispatchers.IO) {

            networkRepository.channelsCategory({ remoteChannelsCategories ->

                viewModelScope.launch(Dispatchers.IO) {
                    mainRepository.saveCategories(
                        ChannelCategory(
                            0,
                            remoteChannelsCategories.data
                        )
                    )
                }
            }, {
                Log.d("okh", it)
            })
        }

    }

    private fun saveNewEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {

            networkRepository.newEpisodes({ remoteNewEpisodes ->

                viewModelScope.launch {
                    mainRepository.saveNewEpisodes(
                        NewEpisodes(
                            0,
                            remoteNewEpisodes.data
                        )
                    )
                }
            }, {
                Log.d("okh", it)
            })
        }
    }

    private fun saveChannels() {
        viewModelScope.launch(Dispatchers.IO) {

            networkRepository.channels({ remoteChannels ->

                viewModelScope.launch {
                    mainRepository.saveChannels(
                        Channels(
                            0,
                            remoteChannels.data
                        )
                    )
                }
            }, {
                Log.d("okh", it)
            })
        }
    }

}