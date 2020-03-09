package com.example.kotlinstudyprojects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database.db"
        ).build()

        db.userDao().getAll().observe(this, Observer { userList ->
            for (user in userList) {
                Log.d("Database", user.toString())
            }
        })

        thread {
            val userList = mutableListOf<User>()

            for (i in 0..100) {
                userList.add(
                    User(
                        i,
                        "Taaka$i",
                        "Taro$i"
                    )
                )
            }

            db.userDao().insertUserList(userList)
        }
    }
}
