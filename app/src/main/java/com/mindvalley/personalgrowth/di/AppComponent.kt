package com.mindvalley.personalgrowth.di

import com.google.gson.Gson
import com.mindvalley.personalgrowth.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, RemoteModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)
    fun gson() : Gson
}