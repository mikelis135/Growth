package com.mindvalley.personalgrowth.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mindvalley.personalgrowth.database.dao.ChannelCategoriesDAO
import org.junit.After
import org.junit.Before

abstract class AppDatabaseTest {

    // system under test
    private var appDatabase: AppDatabase? = null
    var channelCategoriesDAO: ChannelCategoriesDAO? = appDatabase?.channelCategoriesDao()

    @Before
    fun init() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun finish() {
        appDatabase?.close()
    }
}