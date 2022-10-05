package com.personalgrowth.ui

import android.os.Looper
import com.personalgrowth.CoroutineTestRule
import com.personalgrowth.FakeData
import com.personalgrowth.getOrAwaitValue
import com.personalgrowth.repository.FakeMainRepository
import com.personalgrowth.ui.main.MainViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class MainViewModelUnitTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    @Inject
    lateinit var fakeMainRepository: FakeMainRepository

    @Mock
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = MainViewModel(mainCoroutineRule.dispatcher, mainRepository = fakeMainRepository)
    }

    @Test
    fun onSuccess_processCategoriesNetworkCalled() = runBlockingTest {

        FakeMainRepository.failNetwork = false

        viewModel.processCategories()

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        assertEquals(
            FakeData.channelCategoryData.data,
            viewModel.channelCategories.getOrAwaitValue().data
        )

    }

    @Test
    fun onSuccess_processChannelsNetworkCalled() = runBlockingTest {

        FakeMainRepository.failNetwork = false

        viewModel.processChannels()

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        assertEquals(FakeData.channelData.data, viewModel.channels.getOrAwaitValue().data)

    }

    @Test
    fun onError_processNewEpisodesNetworkCalled() = runBlockingTest {

        FakeMainRepository.failNetwork = true

        viewModel.processNewEpisodes()

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        assertEquals(FakeData.errorMessage, viewModel.newEpisodesErrorLD.value)

    }

}