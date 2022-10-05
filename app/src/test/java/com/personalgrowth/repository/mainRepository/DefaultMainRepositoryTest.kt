package com.personalgrowth.repository.mainRepository

import com.personalgrowth.FakeData
import com.personalgrowth.repository.FakeMainRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
@HiltAndroidTest
class DefaultMainRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeMainRepository: FakeMainRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testGetCategories() {
        fakeMainRepository.getCategories({
            assertEquals(FakeData.channelCategoryData.data, it.data)
        }, {

        })
    }

    @Test
    fun testGetChannels() {
        fakeMainRepository.getChannels({
            assertEquals(FakeData.channelData.data, it.data)
        }, {

        })
    }

}