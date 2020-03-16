package com.example.kotlinstudyprojects

import android.app.Application
import androidx.room.Room
import com.example.kotlinstudyprojects.room.AppDatabase

class RoomApplication : Application() {

    val roomDatabase
        get() = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database.db"
        ).build()
}