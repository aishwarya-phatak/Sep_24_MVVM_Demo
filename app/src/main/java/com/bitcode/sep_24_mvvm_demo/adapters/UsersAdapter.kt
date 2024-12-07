package com.bitcode.sep_24_mvvm_demo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcode.sep_24_mvvm_demo.R
import com.bitcode.sep_24_mvvm_demo.databinding.UserViewBinding
import com.bitcode.sep_24_mvvm_demo.models.User

class UsersAdapter(private val users : ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>(){

    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val userViewBinding : UserViewBinding = UserViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_view,null)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            holder.userViewBinding.user = users[position]
    }

    override fun getItemCount() = users.size
}