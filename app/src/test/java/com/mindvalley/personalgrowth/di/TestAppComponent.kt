package com.mindvalley.personalgrowth.di

import com.mindvalley.personalgrowth.ui.MainViewModelUnitTest
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [TestDatabaseModule::class, TestRemoteModule::class])
interface TestAppComponent {
    fun inject(mainViewModelUnitTest: MainViewModelUnitTest)
//    fun inject(mainRepositoryTest: MainRepositoryTest)
}