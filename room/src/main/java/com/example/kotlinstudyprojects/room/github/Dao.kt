package com.example.kotlinstudyprojects.room.github

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoDao {

    @Query("SELECT * FROM Repo")
    fun getAll(): LiveData<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepoList(userList: List<Repo>)
}