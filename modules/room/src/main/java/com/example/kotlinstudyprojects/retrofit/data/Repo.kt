package com.example.kotlinstudyprojects.retrofit.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import com.example.kotlinstudyprojects.room.github.Repo as RoomRepo

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

fun Repo.toRoomModel(): com.example.kotlinstudyprojects.room.github.Repo {
    return RoomRepo(
        id, name, fullName, description, url, forks, watchers, starCount
    )
}