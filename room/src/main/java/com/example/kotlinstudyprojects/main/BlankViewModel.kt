package com.example.kotlinstudyprojects.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinstudyprojects.retrofit.GitHubService
import com.example.kotlinstudyprojects.retrofit.data.toRoomModel
import com.example.kotlinstudyprojects.room.github.RepoDao
import com.example.kotlinstudyprojects.room.user.User
import com.example.kotlinstudyprojects.room.user.UserDao
import kotlinx.coroutines.launch
import kotlin.random.Random

class BlankViewModel(
    val userDao: UserDao,
    val repoDao: RepoDao,
    val gitHubService: GitHubService
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

    fun getRepoAndInsertRoom() {

        viewModelScope.launch {
            val repos = gitHubService.listRepos("android")
            val roomRepos = repos.map { it.toRoomModel() }
            repoDao.insertRepoList(roomRepos)
        }
    }
}
