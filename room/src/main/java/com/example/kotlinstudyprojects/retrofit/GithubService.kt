package com.example.kotlinstudyprojects.retrofit

import com.example.kotlinstudyprojects.retrofit.data.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): List<Repo>
}