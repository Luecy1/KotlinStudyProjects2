package com.example.android_testing.blank

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.android_testing.room.User
import com.example.android_testing.room.UserDao
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class BlankFragmentTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getUser() {
        val tanaka = User(1L, "tanaka", "taro")
        val suzuki = User(2L, "suzuki", "hiroshi")


        val userDao = mock<UserDao>(name = "userDao")
        whenever(userDao.getAll()).thenReturn(liveData {
            emit(
                listOf(tanaka)
            )
        })

        val blankViewModel = BlankViewModel(userDao)

        val observer = mock<Observer<List<User>>>()
        blankViewModel.userList.observeForever(observer)

        verify(observer).onChanged(listOf(tanaka))
    }
}