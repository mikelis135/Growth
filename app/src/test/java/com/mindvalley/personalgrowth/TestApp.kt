package com.mindvalley.personalgrowth

import android.app.Application
import com.mindvalley.personalgrowth.di.*

class TestApp : Application() {

    val testAppComponent by lazy {
        initialiseAppComponent()
    }

    private fun initialiseAppComponent(): TestAppComponent {
        val builder = DaggerTestAppComponent.builder()
        return builder.testDatabaseModule(TestDatabaseModule(this)).build()

    }
}