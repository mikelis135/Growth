package com.mindvalley.personalgrowth.di

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.mindvalley.personalgrowth.database.AppDatabase
import com.mindvalley.personalgrowth.database.DBConstants
import com.mindvalley.personalgrowth.database.dao.ChannelCategoriesDAO
import com.mindvalley.personalgrowth.database.dao.ChannelsDAO
import com.mindvalley.personalgrowth.database.dao.NewEpisodesDAO
import dagger.Module
import dagger.Provides
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

    @Provides
    @Singleton
    fun providesRequestManager(): RequestManager {
        return Glide.with(application.applicationContext)
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
                DBConstants.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
            appDatabase = instance
            return instance
        }

    }

}