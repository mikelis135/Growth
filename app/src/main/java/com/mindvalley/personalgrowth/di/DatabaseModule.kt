package com.mindvalley.personalgrowth.di

import android.app.Application
import androidx.room.Room
import com.mindvalley.personalgrowth.database.AppConstants
import com.mindvalley.personalgrowth.database.AppDatabase
import com.mindvalley.personalgrowth.database.dao.ChannelCategoriesDAO
import com.mindvalley.personalgrowth.database.dao.ChannelsDAO
import com.mindvalley.personalgrowth.database.dao.NewEpisodesDAO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application) {

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
    fun providesDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun providesDatabase(): AppDatabase {

        var appInstance: AppDatabase? = null

        val tempInstance = appInstance
        if (tempInstance != null) {
            return tempInstance
        }

        synchronized(this) {
            val instance = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
            appInstance = instance
            return instance
        }

    }

}