package com.mindvalley.personalgrowth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mindvalley.personalgrowth.database.dao.ChannelCategoriesDAO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DatabaseUnitTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var channelCategoriesDAO: ChannelCategoriesDAO

    @Before
    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//        TestApp().testAppComponent.inject(this)

    }

    @Test
    fun saveChannelCategory()  {
            println("hello")
//            println(channelCategoriesDAO.getCategories().value?.id)
//            println(channelCategory.id)
//            val channelCategory = ChannelCategory(0, Category(listOf()))
////            channelCategoriesDAO.saveCategories(channelCategory)
//            channelCategory.id = 1

            assertEquals(4, 1)

        }

}