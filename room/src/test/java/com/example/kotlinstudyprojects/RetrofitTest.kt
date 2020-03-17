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

        assertEquals("android/.github", listRepos.first().fullName)
    }
}
