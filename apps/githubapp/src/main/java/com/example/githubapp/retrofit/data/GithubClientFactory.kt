package com.example.githubapp.retrofit.data

import com.example.githubapp.retrofit.GitHubService

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GithubClientFactory {

    fun create(): GitHubService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()

        return retrofit.create(GitHubService::class.java)
    }
}