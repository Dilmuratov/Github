package com.example.github.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentFollowersBinding
import com.example.github.presentation.MainViewModel
import com.example.github.ui.adapters.FollowersAdapter
import com.example.github.utils.logTag
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFollowers : Fragment(R.layout.fragment_followers) {
    private lateinit var binding: FragmentFollowersBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val followersAdapter = FollowersAdapter()
    private lateinit var login: String
    private lateinit var type: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFollowersBinding.bind(view)
        binding.recyclerView.adapter = followersAdapter

        initObservers()

        getLogin()

        initListeners()

        getFollowers()
    }

    private fun initListeners() {

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            getFollowers()
        }
    }

    private fun getLogin() {
        val b = arguments!!
        login = b.getString("login", "")
        val isFollowers = b.getBoolean("isFollowers")
        binding.tvLogin.text = login
        binding.tvFollowers.text = if (isFollowers) "Followers" else "Following"
        type = if (isFollowers) "followers" else "following"
    }

    private fun initObservers() {
        mainViewModel.getUserFollowersLiveData.observe(requireActivity()) {
            followersAdapter.submitList(it)
        }

        mainViewModel.messageLiveData.observe(requireActivity()) {
            Log.d(logTag, "Message: $it")
        }

        mainViewModel.errorLiveData.observe(requireActivity()) {
            it.printStackTrace()
        }

        (requireActivity() as MainActivity).hideBottomNavigationView()
    }

    private fun getFollowers() {
        binding.swipeRefreshLayout.isRefreshing = true
        MainScope().launch {
            mainViewModel.getUserFollowers(login, type)
        }
        binding.swipeRefreshLayout.isRefreshing = false
    }
}