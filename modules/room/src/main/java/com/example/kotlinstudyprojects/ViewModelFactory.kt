package com.example.kotlinstudyprojects

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.kotlinstudyprojects.cancel.CancelViewModel
import com.example.kotlinstudyprojects.main.BlankViewModel
import com.example.kotlinstudyprojects.retrofit.GitHubService
import com.example.kotlinstudyprojects.room.AppDatabase
import com.example.kotlinstudyprojects.sub.SubViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val database: AppDatabase,
    private val gitHubService: GitHubService,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(BlankViewModel::class.java) -> BlankViewModel(
                database.userDao(),
                database.RepoDao(),
                gitHubService
            )
            isAssignableFrom(SubViewModel::class.java) -> SubViewModel(
                database.userDao()
            )
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}