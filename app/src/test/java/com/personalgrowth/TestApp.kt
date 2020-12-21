package com.personalgrowth

import android.app.Application
import com.personalgrowth.di.DaggerTestAppComponent
import com.personalgrowth.di.TestAppComponent
import com.personalgrowth.di.TestDatabaseModule
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TestApp : Application() {

    val testAppComponent by lazy {
        initialiseAppComponent()
    }

    private fun initialiseAppComponent(): TestAppComponent {
        val builder = DaggerTestAppComponent.builder()
        return builder.testDatabaseModule(TestDatabaseModule(this)).build()

    }
}