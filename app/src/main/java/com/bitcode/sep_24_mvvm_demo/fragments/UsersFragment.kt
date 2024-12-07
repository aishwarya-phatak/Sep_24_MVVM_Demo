package com.bitcode.sep_24_mvvm_demo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcode.sep_24_mvvm_demo.R
import com.bitcode.sep_24_mvvm_demo.adapters.UsersAdapter
import com.bitcode.sep_24_mvvm_demo.databinding.UsersFragmentBinding
import com.bitcode.sep_24_mvvm_demo.factory.MyViewModelFactory
import com.bitcode.sep_24_mvvm_demo.network.UsersApiService
import com.bitcode.sep_24_mvvm_demo.repository.UserRepository
import com.bitcode.sep_24_mvvm_demo.viewmodels.UsersViewModel

class UsersFragment : Fragment() {
    private lateinit var usersFragmentBinding: UsersFragmentBinding
    private lateinit var usersViewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usersFragmentBinding = UsersFragmentBinding.inflate(inflater)
            initViews()
            initViewModels()
            initAdapters()
            initObservers()
            initListeners()

        usersViewModel.fetchUser()
        return usersFragmentBinding.root
    }

    private fun initViews(){
        usersFragmentBinding.recyclerViewForUsers.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun initAdapters(){
        usersAdapter = UsersAdapter(usersViewModel.users)
        usersFragmentBinding.recyclerViewForUsers.adapter = usersAdapter

    }

    private fun initListeners(){

        usersFragmentBinding.recyclerViewForUsers.addOnScrollListener(
            object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                        usersViewModel.fetchUser()
                    }
                }
            }
        )

        usersFragmentBinding.btnAddUser.setOnClickListener{
            addUserFragment()
        }
    }

    private fun addUserFragment(){
        val addUserFragment = AddUserFragment()
       parentFragmentManager.beginTransaction()
           .add(R.id.main,addUserFragment,null)
           .addToBackStack(null)
           .commit()
    }

    private fun initViewModels(){
        usersViewModel = ViewModelProvider(this,
            MyViewModelFactory(UserRepository(UsersApiService.getInstance())))
            .get(UsersViewModel::class.java)
    }

    private fun initObservers(){
        usersViewModel.usersUpdateAvailableLiveData.observe(
            viewLifecycleOwner
        ){
            if(it){
                usersAdapter.notifyDataSetChanged()
            }
        }
    }
}