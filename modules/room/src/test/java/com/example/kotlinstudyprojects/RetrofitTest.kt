package com.example.kotlinstudyprojects

import com.example.kotlinstudyprojects.retrofit.GithubClientFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class RetrofitTest {

    @Test
    fun client() {
        val client = GithubClientFactory.create()

        val listRepos = runBlocking {
            val listRepos = client.listRepos("android")
            listRepos
        }

        val repo = listRepos.first()
        assertEquals("android/.github", repo.fullName)
        assertEquals(".github", repo.name)
        assertEquals("Repo hosting default community health files.", repo.description)
        assertEquals("https://api.github.com/repos/android/.github", repo.url)

    }
}
