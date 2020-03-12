package com.example.kotlinstudyprojects.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinstudyprojects.room.User
import com.example.kotlinstudyprojects.room.UserDao
import kotlinx.coroutines.launch
import kotlin.random.Random

class BlankViewModel(
    val userDao: UserDao
) : ViewModel() {

    fun insertUser() {
        viewModelScope.launch {

            val uid = Random.nextInt()
            val ramdomUser = User(
                uid,
                "Tnaka$uid", "Taro"
            )

            userDao.insertUser(ramdomUser)
        }
    }
}
