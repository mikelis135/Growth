package com.personalgrowth.di

import com.personalgrowth.repository.mainRepository.DefaultMainRepositoryTest
import com.personalgrowth.ui.MainViewModelUnitTest
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [TestDatabaseModule::class, TestRemoteModule::class])
interface TestAppComponent {
    fun inject(mainViewModelUnitTest: MainViewModelUnitTest)
    fun inject(defaultMainRepositoryTest: DefaultMainRepositoryTest)
}