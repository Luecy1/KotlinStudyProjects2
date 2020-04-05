package com.example.android_testing.blank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_testing.room.User
import com.example.android_testing.room.UserDao
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class BlankViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    val userList = userDao.getAll()

    fun insertUser(user: User): Job {
        return viewModelScope.launch {
            userDao.insertUser(user)
        }
    }
}
