package com.personalgrowth.di

import com.google.gson.Gson
import com.personalgrowth.data.channel.DefaultChannelsLocal
import com.personalgrowth.data.channelCategory.DefaultChannelCategoryLocal
import com.personalgrowth.data.newEpisodes.DefaultNewEpisodeLocal
import com.personalgrowth.database.dao.ChannelCategoriesDAO
import com.personalgrowth.database.dao.ChannelsDAO
import com.personalgrowth.database.dao.NewEpisodesDAO
import com.personalgrowth.local.FakeChannelCategoryLocal
import com.personalgrowth.local.FakeChannelsLocal
import com.personalgrowth.local.FakeNewEpisodesLocal
import com.personalgrowth.remote.ApiService
import com.personalgrowth.remote.FakeNetworkRepository
import com.personalgrowth.repository.FakeMainRepository
import com.personalgrowth.repository.mainRepository.DefaultMainRepository
import com.personalgrowth.repository.mainRepository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [RemoteModule::class])
class TestRemoteModule {

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesNetworkRepository(): FakeNetworkRepository {
        return FakeNetworkRepository()
    }

    @Singleton
    @Provides
    fun providesMainRepository(): MainRepository {
        return FakeMainRepository()
    }

    @Singleton
    @Provides
    fun providesDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Singleton
    @Provides
    fun provideMainRepository(
        fakeNetworkRepository: FakeNetworkRepository,
        fakeChannelCategoryLocal: FakeChannelCategoryLocal,
        fakeNewEpisodesLocal: FakeNewEpisodesLocal,
        fakeChannelsLocal: FakeChannelsLocal,
        coroutineDispatcher: CoroutineDispatcher
    ): DefaultMainRepository {
        return DefaultMainRepository(
            fakeNetworkRepository,
            fakeChannelCategoryLocal,
            fakeNewEpisodesLocal,
            fakeChannelsLocal,
            coroutineDispatcher
        )
    }

    @Provides
    fun providesFakeChannelCategoryLocal(
        channelCategoriesDAO: ChannelCategoriesDAO,
        coroutineDispatcher: CoroutineDispatcher
    ): DefaultChannelCategoryLocal {
        return DefaultChannelCategoryLocal(channelCategoriesDAO, coroutineDispatcher)
    }

    @Provides
    fun providesFakeChannelLocal(
        channelsDAO: ChannelsDAO,
        coroutineDispatcher: CoroutineDispatcher
    ): DefaultChannelsLocal {
        return DefaultChannelsLocal(channelsDAO, coroutineDispatcher)
    }

    @Provides
    fun providesFakeNewEpisodeLocal(
        newEpisodesDAO: NewEpisodesDAO,
        coroutineDispatcher: CoroutineDispatcher
    ): DefaultNewEpisodeLocal {
        return DefaultNewEpisodeLocal(newEpisodesDAO, coroutineDispatcher)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
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
            .baseUrl("https://pastebin.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}