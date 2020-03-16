package com.example.githubapp

import com.example.githubapp.retrofit.data.GithubClientFactory
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun client() {
        val client = GithubClientFactory.create()
        val listRepos = client.listRepos("android")
        val response = listRepos.execute()

        assertEquals("android/.github", response.body()?.first()?.full_name)
    }
}

