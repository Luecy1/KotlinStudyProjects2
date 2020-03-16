package com.example.kotlinstudyprojects

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kotlinstudyprojects.room.AppDatabase
import com.example.kotlinstudyprojects.room.User
import com.example.kotlinstudyprojects.room.UserDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user: User = User(
            1,
            "Tanaka",
            "Taro"
        )
        runBlocking {
            userDao.insertUser(user)
            val userFromRoom = userDao.loadById(userId = user.uid).value

            assertThat(userFromRoom?.firstName, equalTo(user.firstName))
        }
    }
}
