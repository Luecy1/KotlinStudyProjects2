package com.example.kotlinstudyprojects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
