package com.example.kotlinstudyprojects.sub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinstudyprojects.room.User

class SubViewModel : ViewModel() {

    val userList: MutableLiveData<List<User>>
        get() = _userList

    private val _userList = MutableLiveData<List<User>>()

    fun user() {

        val userList = (0..20).map {
            User(it, "Tanaka", "Tao")
        }

        _userList.postValue(userList)
    }

}
