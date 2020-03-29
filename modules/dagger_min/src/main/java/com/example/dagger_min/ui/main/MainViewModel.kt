package com.example.dagger_min.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dagger_min.User
import com.example.dagger_min.repo.GithubRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    fun getUserInfo() = liveData<User> {
        emit(githubRepository.getUserInfo())
    }
}
