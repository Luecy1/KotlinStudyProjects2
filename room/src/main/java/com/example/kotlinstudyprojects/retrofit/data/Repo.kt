package com.example.kotlinstudyprojects.retrofit.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    val id: Long,
    val name: String,
    @Json(name = "full_name")
    val fullName: String
)