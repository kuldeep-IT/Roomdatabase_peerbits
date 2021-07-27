package com.peerbitskuldeep.roomdatabasedemo.room

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    fun readAllData(): LiveData<List<User>> = userDao.readAll()

    suspend fun addUser(user: User) = userDao.addUser(user)

    suspend fun updateData(user: User)
    {
        userDao.updateData(user)
    }

    suspend fun deleteData(user: User)
    {
        userDao.deleteData(user)
    }

    suspend fun deleteAll()
    {
        userDao.deleteAll()
    }

}