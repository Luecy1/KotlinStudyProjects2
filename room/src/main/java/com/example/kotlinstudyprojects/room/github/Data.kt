package com.example.kotlinstudyprojects.room.github

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Repo(
    @PrimaryKey
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val url: String,
    val forks: Long,
    val watchers: Long,
    val starCount: Long
)