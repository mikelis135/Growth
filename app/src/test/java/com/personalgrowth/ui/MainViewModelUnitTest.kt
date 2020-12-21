package com.personalgrowth.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.personalgrowth.CoroutineTestRule
import com.personalgrowth.FakeData
import com.personalgrowth.TestApp
import com.personalgrowth.getOrAwaitValue
import com.personalgrowth.repository.FakeMainRepository
import com.personalgrowth.repository.mainRepository.DefaultMainRepository
import com.personalgrowth.ui.main.MainViewModel
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
    lateinit var defaultMainRepository: DefaultMainRepository

    @Inject
    lateinit var fakeMainRepository: FakeMainRepository

    @Inject
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        TestApp().testAppComponent.inject(this)
    }

    @Test
    fun onSuccess_processCategoriesNetworkCalled() = runBlockingTest {

        FakeMainRepository.failNetwork = false

        viewModel.processCategories()

        assertEquals(
            FakeData.channelCategoryData.data,
            viewModel.channelCategories.getOrAwaitValue().data
        )

        println(viewModel.channelCategories.getOrAwaitValue().data.toString())

    }

    @Test
    fun onSuccess_processChannelsNetworkCalled() = runBlockingTest {

        FakeMainRepository.failNetwork = false

        viewModel.processChannels()

        assertEquals(FakeData.channelData.data, viewModel.channels.getOrAwaitValue().data)

    }

    @Test
    fun onError_processNewEpisodesNetworkCalled() = runBlockingTest {

        FakeMainRepository.failNetwork = true

        viewModel.processNewEpisodes()

        assertEquals(viewModel.newEpisodesError.getOrAwaitValue(), FakeData.errorMessage)

    }

}