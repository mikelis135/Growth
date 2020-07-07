package com.mindvalley.personalgrowth.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.mindvalley.personalgrowth.InstantExecutorExtension
import com.mindvalley.personalgrowth.LiveDataTestUtil
import com.mindvalley.personalgrowth.database.AppDatabaseTest
import com.mindvalley.personalgrowth.database.entity.ChannelCategory
import com.mindvalley.personalgrowth.model.Category
import com.mindvalley.personalgrowth.model.CategoryNames
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class MainRepositoryTest : AppDatabaseTest() {

    @Rule
    var rule = InstantTaskExecutorRule()

    lateinit var mainRepository: MainRepository

    var category: LiveData<ChannelCategory>? = null
    @BeforeEach
    fun setUp() {
//        channelCategoriesDAO = mock(ChannelCategoriesDAO::class.java)

    }

    @Test
    fun getChannelCategories() {

        val channelCategoryLiveData = LiveDataTestUtil<ChannelCategory>()
        //arrange
//        `when`(channelCategoriesDAO.getCategories()).thenReturn(channelCategoryLiveData)
    }

    @Test
    fun getNewEpisodes() {
    }

    @Test
    fun getChannels() {
    }

    @Test
    fun getCategories() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun saveCategory() {
        val categoryNamesList = mutableListOf<CategoryNames>()
        categoryNamesList.add(0, CategoryNames("Taiwo"))

        val channelCategory = ChannelCategory(0, Category(categoryNamesList))


        runBlockingTest {

            async {
                channelCategoriesDAO?.saveCategories(channelCategory)

            }.await()

        }

        // read
        val liveDataTestUtil: LiveDataTestUtil<ChannelCategory> = LiveDataTestUtil()

        runBlockingTest {

            async {
                 category = channelCategoriesDAO?.getCategories()
                val r =  category?.value
            }.await()


        }


        category?.let {

            val insertedChannelCategory: ChannelCategory = liveDataTestUtil.getValue(it)
            println(liveDataTestUtil.getValue(it).id)
            assertEquals(channelCategory.id, "a ${insertedChannelCategory.id}")

        }


    }

    @Test
    fun testGetNewEpisodes() {
    }

    @Test
    fun saveNewEpisodes() {
    }

    @Test
    fun testGetChannels() {
    }
}