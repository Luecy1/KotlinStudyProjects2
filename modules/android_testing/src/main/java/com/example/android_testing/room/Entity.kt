package com.example.android_testing.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Long,
    val firstName: String,
    val lastName: String
)