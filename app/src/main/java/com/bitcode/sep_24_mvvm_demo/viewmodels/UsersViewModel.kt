package com.bitcode.sep_24_mvvm_demo.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcode.sep_24_mvvm_demo.models.User
import com.bitcode.sep_24_mvvm_demo.models.UserPostModel
import com.bitcode.sep_24_mvvm_demo.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(private var userRepository: UserRepository) : ViewModel(){
    val usersUpdateAvailableLiveData = MutableLiveData<Boolean>()
    val users = ArrayList<User>()
    private var pageNumber : Int = 0

    fun fetchUser(){
        CoroutineScope(Dispatchers.IO).launch {
            val users = userRepository.fetchUsers(++pageNumber)

            withContext(Dispatchers.Main){
                this@UsersViewModel.users.addAll(users)
                usersUpdateAvailableLiveData.postValue(true)
            }
        }
    }

    private val userPostLiveData = MutableLiveData<Boolean>()
    fun addUser(userPostModel: UserPostModel){
        CoroutineScope(Dispatchers.IO).launch {
            val responsePostUser = userRepository.addUser(userPostModel)
            Log.e("tag",responsePostUser.toString())
            withContext(Dispatchers.Main){
                userPostLiveData.postValue(true)
            }
        }
    }
}