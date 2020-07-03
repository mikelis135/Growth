package com.mindvalley.personalgrowth.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()

    init {
        a()
    }

    fun a(){
        name.value = "ok"
    }

}