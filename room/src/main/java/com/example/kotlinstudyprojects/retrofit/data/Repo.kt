package com.example.kotlinstudyprojects.retrofit.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    val id: Long,
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    val description: String?,
    val url: String,
    val forks: Long,
    val watchers: Long,
    @Json(name = "stargazers_count")
    val starCount: Long
)