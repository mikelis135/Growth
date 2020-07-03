package com.mindvalley.personalgrowth.di

import android.app.Application
import androidx.room.Room
import com.mindvalley.personalgrowth.model.AppDatabase
import com.mindvalley.personalgrowth.model.ChannelCategoriesDAO
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
    fun providesDatabase(): AppDatabase {

        var INSTANCE: AppDatabase? = null

        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }

        synchronized(this) {
            val instance = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                "channel_db"
            ).fallbackToDestructiveMigration().build()
            INSTANCE = instance
            return instance
        }

    }

}