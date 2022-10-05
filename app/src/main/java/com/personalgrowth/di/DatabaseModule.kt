package com.personalgrowth.di

import android.content.Context
import androidx.room.Room
import com.personalgrowth.database.AppConstants
import com.personalgrowth.database.AppDatabase
import com.personalgrowth.database.dao.ChannelCategoriesDAO
import com.personalgrowth.database.dao.ChannelsDAO
import com.personalgrowth.database.dao.NewEpisodesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

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
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {

        var appInstance: AppDatabase? = null

        val tempInstance = appInstance
        if (tempInstance != null) {
            return tempInstance
        }

        synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
            appInstance = instance
            return instance
        }

    }

}