package com.peerbitskuldeep.roomdatabasedemo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.peerbitskuldeep.roomdatabasedemo.room.User
import com.peerbitskuldeep.roomdatabasedemo.room.UserDataBase
import com.peerbitskuldeep.roomdatabasedemo.room.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    var readAllData: LiveData<List<User>>
    private var repository: UserRepository

    init {

        val userDao = UserDataBase.getDatabase(application).userDao()
        repository = UserRepository(userDao)

        readAllData = repository.readAllData()
    }

    fun addUser(user: User){

        viewModelScope.launch(Dispatchers.IO) {

            repository.addUser(user)

        }
    }

    fun updateData(user: User)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(user)
        }
    }

    fun deleteData(user: User)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteData(user)
        }
    }

    fun deleteAll()
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

}