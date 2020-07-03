package com.mindvalley.personalgrowth

import android.app.Application
import com.mindvalley.personalgrowth.di.AppComponent
import com.mindvalley.personalgrowth.di.DaggerAppComponent

class App : Application() {

    val appComponent by lazy {
        initialiseAppComponent()
    }

    private fun initialiseAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}