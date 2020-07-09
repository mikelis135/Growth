package com.mindvalley.personalgrowth.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mindvalley.personalgrowth.CoroutineTestRule
import com.mindvalley.personalgrowth.TestApp
import com.mindvalley.personalgrowth.database.entity.ChannelCategory
import com.mindvalley.personalgrowth.database.entity.Channels
import com.mindvalley.personalgrowth.repository.MainRepository
import com.mindvalley.personalgrowth.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModelUnitTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var mainRepository: MainRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        TestApp().testAppComponent.inject(this)

    }

    private val errorMessage = "pastebin.com"

    @Test
    fun onSuccess_processCategoriesNetworkCalled() = runBlockingTest {
        val callback: (ChannelCategory) -> Unit = mock()

        viewModel.processCategories()

        mainRepository.getCategories({
            assertEquals(callback, it)
        }, {

        })

    }

    @Test
    fun onError_processCategoriesNetworkCalled() = runBlockingTest {

        val error: (String) -> Unit = mock()

        viewModel.processCategories()

        mainRepository.getCategories({

        }, {

            assertEquals(errorMessage, it)
            assertEquals(error, it)
        })

    }

    @Test
    fun onSuccess_processNewEpisodesNetworkCalled() = runBlockingTest {
        val callback: (ChannelCategory) -> Unit = mock()

        viewModel.processNewEpisodes()

        mainRepository.getNewEpisodes({
            assertEquals(callback, it)
        }, {

        })
    }

    @Test
    fun onError_processNewEpisodesNetworkCalled() = runBlockingTest {

        val error: (String) -> Unit = mock()

        viewModel.processNewEpisodes()

        mainRepository.getNewEpisodes({

        }, {
            assertEquals(errorMessage, it)
            assertEquals(error, it)

        })

    }

    @Test
    fun onSuccess_processChannelsNetworkCalled() = runBlockingTest {

        val callback: (Channels) -> Unit = mock()

        viewModel.processChannels()

        mainRepository.getChannels({
            assertEquals(callback, it)
        }, {

        })
    }

    @Test
    fun onError_processChannelsNetworkCalled() = runBlockingTest {

        val error: (String) -> Unit = mock()

        viewModel.processChannels()

        mainRepository.getChannels({

        }, {
            assertEquals(errorMessage, it)
            assertEquals(error, it)
        })

    }

}