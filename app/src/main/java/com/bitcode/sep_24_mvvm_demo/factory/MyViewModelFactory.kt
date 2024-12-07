package com.bitcode.sep_24_mvvm_demo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcode.sep_24_mvvm_demo.repository.UserRepository
import com.bitcode.sep_24_mvvm_demo.viewmodels.UsersViewModel

class MyViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(userRepository) as T
    }
}