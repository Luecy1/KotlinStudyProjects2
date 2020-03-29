package com.example.dagger_min

import retrofit2.http.GET

interface GithubApi {

    @GET("users/android")
    suspend fun getUserInfo(): User
}

data class User(
    val login: String,
    val url: String,
    val blog: String
)