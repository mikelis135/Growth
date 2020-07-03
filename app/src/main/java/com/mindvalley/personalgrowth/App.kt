package com.mindvalley.personalgrowth

import android.app.Application
import com.mindvalley.personalgrowth.di.AppComponent
import com.mindvalley.personalgrowth.di.DaggerAppComponent
import com.mindvalley.personalgrowth.di.DatabaseModule

class App : Application() {

    val appComponent by lazy {
        initialiseAppComponent()
    }

    private fun initialiseAppComponent(): AppComponent {
        val builder = DaggerAppComponent.builder()
        return builder.databaseModule(DatabaseModule(this)).build()

    }
}