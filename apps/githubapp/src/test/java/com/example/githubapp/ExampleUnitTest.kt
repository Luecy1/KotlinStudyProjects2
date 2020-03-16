package com.example.githubapp

import com.example.githubapp.retrofit.data.GithubClientFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
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

    @ExperimentalCoroutinesApi
    @Test
    fun client() {
        val client = GithubClientFactory.create()

        val listRepos = runBlocking {
            val listRepos = client.listRepos("android")
            listRepos
        }

        assertEquals("android/.github", listRepos.first().full_name)
    }
}

