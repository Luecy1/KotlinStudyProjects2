package com.example.kotlinstudyprojects

import android.app.Application
import android.os.Debug
import androidx.room.Room
import com.example.kotlinstudyprojects.room.AppDatabase
import timber.log.Timber

class RoomApplication : Application() {

    val roomDatabase
        get() = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database.db"
        ).build()


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}