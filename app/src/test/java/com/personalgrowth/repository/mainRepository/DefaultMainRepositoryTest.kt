package com.personalgrowth.repository.mainRepository

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.personalgrowth.CoroutineTestRule
import com.personalgrowth.FakeData
import com.personalgrowth.TestApp
import com.personalgrowth.database.entity.ChannelCategory
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.rules.TestRule
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DefaultMainRepositoryTest : TestCase() {

    @Inject
    lateinit var defaultMainRepository: DefaultMainRepository

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    @ExperimentalCoroutinesApi
    override fun setUp() {
        TestApp().testAppComponent.inject(this)
        super.setUp()
    }

    fun testGetCategories() {
        defaultMainRepository.getCategories({
            assertEquals(FakeData.channelCategoryData.data, it.data)
        }, {

        })
    }

    fun testGetChannels() {
        defaultMainRepository.getChannels({
            assertEquals(FakeData.channelData.data, it.data)
        }, {

        })
    }

}