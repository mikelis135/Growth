package com.personalgrowth.di

import com.personalgrowth.data.channel.DefaultChannelsLocal
import com.personalgrowth.data.channelCategory.DefaultChannelCategoryLocal
import com.personalgrowth.data.newEpisodes.DefaultNewEpisodeLocal
import com.personalgrowth.database.AppConstants
import com.personalgrowth.database.dao.ChannelCategoriesDAO
import com.personalgrowth.database.dao.ChannelsDAO
import com.personalgrowth.database.dao.NewEpisodesDAO
import com.personalgrowth.remote.ApiService
import com.personalgrowth.remote.DefaultNetworkRepository
import com.personalgrowth.repository.mainRepository.DefaultMainRepository
import com.personalgrowth.repository.mainRepository.MainRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesNetworkRepository(apiService: ApiService): DefaultNetworkRepository {
        return DefaultNetworkRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideMainRepository(
        defaultNetworkRepository: DefaultNetworkRepository,
        defaultChannelCategoryLocal: DefaultChannelCategoryLocal,
        defaultNewEpisodeLocal: DefaultNewEpisodeLocal,
        defaultChannelsLocal: DefaultChannelsLocal,
        coroutineDispatcher: CoroutineDispatcher
    ): MainRepository {
        return DefaultMainRepository(
            defaultNetworkRepository,
            defaultChannelCategoryLocal,
            defaultNewEpisodeLocal,
            defaultChannelsLocal,
            coroutineDispatcher
        )
    }

    @Provides
    fun providesDefaultChannelCategoryLocal(
        channelCategoriesDAO: ChannelCategoriesDAO,
        coroutineDispatcher: CoroutineDispatcher
    ): DefaultChannelCategoryLocal {
        return DefaultChannelCategoryLocal(channelCategoriesDAO, coroutineDispatcher)
    }


    @Provides
    fun providesDefaultChannelLocal(
        channelsDAO: ChannelsDAO,
        coroutineDispatcher: CoroutineDispatcher
    ): DefaultChannelsLocal {
        return DefaultChannelsLocal(channelsDAO, coroutineDispatcher)
    }

    @Provides
    fun providesDefaultNewEpisodeLocal(
        newEpisodesDAO: NewEpisodesDAO,
        coroutineDispatcher: CoroutineDispatcher
    ): DefaultNewEpisodeLocal {
        return DefaultNewEpisodeLocal(newEpisodesDAO, coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun providesDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun providesPlainOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .apply {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addNetworkInterceptor(logging)

            }
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}