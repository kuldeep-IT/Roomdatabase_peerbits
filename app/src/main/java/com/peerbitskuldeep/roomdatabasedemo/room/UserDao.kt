package com.peerbitskuldeep.roomdatabasedemo.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateData(user: User)

    @Delete
    suspend fun deleteData(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAll(): LiveData<List<User>>

}