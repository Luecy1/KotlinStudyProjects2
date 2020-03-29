package com.example.dagger_min.repo

import com.example.dagger_min.GithubApi
import com.example.dagger_min.User
import javax.inject.Inject

interface GithubRepository {
   suspend fun getUserInfo(): User
}


class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override suspend fun getUserInfo(): User {
        return githubApi.getUserInfo()
    }
}