package com.example.android_testing.di

import com.example.android_testing.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainFragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}