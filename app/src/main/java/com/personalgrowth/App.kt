package com.personalgrowth

import android.app.Application
import com.personalgrowth.di.AppComponent
import com.personalgrowth.di.DaggerAppComponent
import com.personalgrowth.di.DatabaseModule

class App : Application() {

    val appComponent by lazy {
        initialiseAppComponent()
    }

    private fun initialiseAppComponent(): AppComponent {
        val builder = DaggerAppComponent.builder()
        return builder.databaseModule(DatabaseModule(this)).build()
    }

}