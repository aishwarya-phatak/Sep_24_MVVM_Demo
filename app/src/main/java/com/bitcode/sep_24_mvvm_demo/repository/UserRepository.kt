package com.bitcode.sep_24_mvvm_demo.repository

import com.bitcode.sep_24_mvvm_demo.models.User
import com.bitcode.sep_24_mvvm_demo.models.UserPostModel
import com.bitcode.sep_24_mvvm_demo.models.UserPostResponse
import com.bitcode.sep_24_mvvm_demo.network.UsersApiService

class UserRepository(private var usersApiService: UsersApiService){

    suspend fun fetchUsers(pageNumber : Int) : ArrayList<User>{
        return usersApiService.fetchUsers(pageNumber).users
    }

    suspend fun addUser(userPostModel: UserPostModel) : UserPostResponse{
        return  usersApiService.addUser(userPostModel)
    }
}