package com.example.dagger_subcomponents

import android.app.Application
import com.example.dagger_subcomponents.di.DaggerAppComponent
import timber.log.Timber

class DaggerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val appComponent = DaggerAppComponent.factory().create()

    }
}