package com.example.kotlinstudyprojects.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinstudyprojects.room.github.Repo
import com.example.kotlinstudyprojects.room.github.RepoDao
import com.example.kotlinstudyprojects.room.user.User
import com.example.kotlinstudyprojects.room.user.UserDao

@Database(
    entities = [User::class, Repo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun RepoDao(): RepoDao
}