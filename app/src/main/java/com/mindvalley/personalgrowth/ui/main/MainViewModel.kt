package com.mindvalley.personalgrowth.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindvalley.personalgrowth.model.Category
import com.mindvalley.personalgrowth.model.CategoryNames
import com.mindvalley.personalgrowth.model.ChannelCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    var channelCategories: LiveData<ChannelCategory> get() = mainRepository.channelCategories

    init {
        channelCategories = mainRepository.channelCategories
    }

    fun saveCategories() {
        val categoryNames = arrayListOf<CategoryNames>()
        categoryNames.add(CategoryNames("Taiwo"))
        categoryNames.add(CategoryNames("Kehinde"))
        categoryNames.add(CategoryNames("Femi"))
        val category = Category(categoryNames)
        val channelCategory = ChannelCategory(0, category)

        viewModelScope.launch(Dispatchers.IO) {
            delay(
                3000
            )
            mainRepository.saveCategories(channelCategory)
        }
    }

}