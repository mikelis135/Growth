package com.personalgrowth.di

import com.personalgrowth.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, RemoteModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)
}