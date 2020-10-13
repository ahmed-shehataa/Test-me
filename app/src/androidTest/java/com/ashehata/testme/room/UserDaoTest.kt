package com.ashehata.testme.room

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var appDatabase: AppDatabase
    lateinit var userDao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        userDao = appDatabase.userDao()
    }

    @After
    fun release() {
        appDatabase.close()
    }

    @Test
    fun insertUser() {
        runBlockingTest {
            // arrange
            val user = User(firstName = "ahmed", lastName = "shehata")

            // act
            userDao.insert(user = user)

            var users: List<User>? = null
            async(Dispatchers.Unconfined) {
                userDao.getAll().collect {
                    users = it
                }
            }.cancel()

            //assert
            val result = users?.contains(user)
            assertThat(result)
        }
    }


    @Test
    fun insertListUsers() {
        runBlockingTest {
            // arrange
            val users = listOf(
                User(uid = 1, firstName = "ahmed", lastName = "shehata"),
                User(uid = 2,firstName = "ali", lastName = "shehata"),
                User(uid = 3,firstName = "mom", lastName = "shehata"),
                User(uid = 4,firstName = "4141", lastName = "shehata")
            )

            // act
            userDao.insertAll(users)

            var result: List<User>? = null
            async(Dispatchers.Unconfined) {
                userDao.getAll().collect {
                    result = it
                }
            }.cancel()

            //assert
            assertThat(result).isEqualTo(users)
        }
    }

}