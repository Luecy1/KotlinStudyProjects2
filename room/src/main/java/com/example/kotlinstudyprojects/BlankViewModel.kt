package com.example.kotlinstudyprojects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import kotlinx.coroutines.launch

class BlankViewModel(
) : ViewModel() {

    fun insertUser() {
        viewModelScope.launch {

        }
    }
}
