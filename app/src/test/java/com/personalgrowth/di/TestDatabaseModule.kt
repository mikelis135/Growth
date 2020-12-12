package com.personalgrowth.di

import android.app.Application
import androidx.room.Room
import com.personalgrowth.database.AppConstants
import com.personalgrowth.database.AppDatabase
import com.personalgrowth.database.dao.ChannelCategoriesDAO
import com.personalgrowth.database.dao.ChannelsDAO
import com.personalgrowth.database.dao.NewEpisodesDAO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    fun providesDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
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

        synchronized(this) {
            val instance = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
            appDatabase = instance
            return instance
        }

//        return  Room.inMemoryDatabaseBuilder(
//            application.applicationContext,
//            AppDatabase::class.java
//        ).build()

    }

}