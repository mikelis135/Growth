package com.personalgrowth.di

import android.app.Application
import com.personalgrowth.database.AppDatabase
import com.personalgrowth.database.dao.ChannelCategoriesDAO
import com.personalgrowth.database.dao.ChannelsDAO
import com.personalgrowth.database.dao.NewEpisodesDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestDatabaseModule(private val application: Application) {

    @Singleton
    @Provides
    fun providesChannelCategoriesDao(appDatabase: AppDatabase): ChannelCategoriesDAO {
        return appDatabase.channelCategoriesDao()
    }

    @Singleton
    @Provides
    fun providesNewEpisodesDao(appDatabase: AppDatabase): NewEpisodesDAO {
        return appDatabase.newEpisodesDao()
    }

    @Singleton
    @Provides
    fun providesChannelsDAO(appDatabase: AppDatabase): ChannelsDAO {
        return appDatabase.channelsDao()
    }

    @Singleton
    @Provides
    fun providesDatabase(): AppDatabase {

        var appDatabase: AppDatabase? = null

        val tempInstance = appDatabase
        if (tempInstance != null) {
            return tempInstance
        }

        return appDatabase!!
//
//        synchronized(this) {
//            val instance = Room.inMemoryDatabaseBuilder(
//                ApplicationProvider.getApplicationContext(),
//                AppDatabase::class.java
//            ).allowMainThreadQueries().build()
//            appDatabase = instance
//            return instance
//        }

    }

}