package com.mindvalley.personalgrowth.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mindvalley.personalgrowth.TestApp
import com.mindvalley.personalgrowth.TestCoroutineRule
import com.mindvalley.personalgrowth.database.entity.ChannelCategory
import com.mindvalley.personalgrowth.database.entity.Channels
import com.mindvalley.personalgrowth.repository.MainRepository
import com.mindvalley.personalgrowth.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.validateMockitoUsage
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
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
    val testCoroutineRule = TestCoroutineRule()

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
    fun onSuccess_processCategoriesNetworkCalled() =
        testCoroutineRule.testCoroutineDispatcher.runBlockingTest {
            println("hello")
            val callback: (ChannelCategory) -> Unit = mock()

            viewModel.processCategories()

            verify(callback, atLeastOnce())

            mainRepository.getCategories({
                assertEquals(callback, it)
            }, {

            })
        }

    @Test
    fun onError_processCategoriesNetworkCalled() =
        testCoroutineRule.testCoroutineDispatcher.runBlockingTest {

            val error: (String) -> Unit = mock()

            viewModel.processCategories()

            verify(error, atLeastOnce())

            mainRepository.getCategories({

            }, {
                assertEquals(error, it)
                assertEquals(errorMessage, it)
            })

        }

    @Test
    fun onSuccess_processNewEpisodesNetworkCalled() =
        testCoroutineRule.testCoroutineDispatcher.runBlockingTest {

            val callback: (ChannelCategory) -> Unit = mock()

            viewModel.processNewEpisodes()

            verify(callback, atLeastOnce())

            mainRepository.getNewEpisodes({
                assertEquals(callback, it)
            }, {

            })
        }

    @Test
    fun onError_processNewEpisodesNetworkCalled() =
        testCoroutineRule.testCoroutineDispatcher.runBlockingTest {

            val error: (String) -> Unit = mock()

            viewModel.processNewEpisodes()

            verify(error, atLeastOnce())

            mainRepository.getNewEpisodes({

            }, {
                assertEquals(errorMessage, it)
                assertEquals(error, it)
            })

        }

    @Test
    fun onSuccess_processChannelsNetworkCalled() =
        testCoroutineRule.testCoroutineDispatcher.runBlockingTest {

            val callback: (Channels) -> Unit = mock()

            viewModel.processChannels()

            verify(callback, atLeastOnce())

            mainRepository.getChannels({
                assertEquals(callback, it)
            }, {

            })
        }

    @Test
    fun onError_processChannelsNetworkCalled() =
        testCoroutineRule.testCoroutineDispatcher.runBlockingTest {

            val error: (String) -> Unit = mock()

            viewModel.processChannels()

            verify(error, atLeastOnce())

            mainRepository.getChannels({

            }, {
                assertEquals(errorMessage, it)
            })

        }

    @After
    fun validate() {
        validateMockitoUsage()
    }

}