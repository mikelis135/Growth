package com.mindvalley.personalgrowth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mindvalley.personalgrowth.ui.main.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelUnitTest {

    @Rule
    @get:Rule @JvmField  var testRule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
       viewModel = MainViewModel()
    }

    @Test

    fun text() {
        assertEquals(viewModel.name.value, "ok")
    }
}