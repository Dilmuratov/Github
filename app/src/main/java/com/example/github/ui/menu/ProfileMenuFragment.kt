package com.example.github.ui.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.github.R
import com.example.github.databinding.FragmentMenuProfileBinding
import com.example.github.presentation.MainViewModel
import com.example.github.utils.getStringFromPref
import com.example.github.utils.logTag
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileMenuFragment : Fragment(R.layout.fragment_menu_profile) {
    private lateinit var binding: FragmentMenuProfileBinding
    private val mainViewModel: MainViewModel by viewModel()
    private var adapter = RepositoryAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuProfileBinding.bind(view)
        binding.recyclerView.adapter = adapter

        initObservers()

        initListeners()

        updateAll()

    }

    private fun updateAll() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            getData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initListeners() {
        binding.ivSettings.setOnClickListener {
            getData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    private fun getData() {
        MainScope().launch {
            mainViewModel.getUserProfileInfo()
            mainViewModel.getUserRepositories()
            Log.d(logTag, "Access key: ${getStringFromPref("access_key")}")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        mainViewModel.getUserProfileLiveData.observe(requireActivity()) {
            if (it != null) {
                binding.tvFullName.text = it.name
                binding.tvUsername.text = it.login
                binding.tvLocation.text = it.location
                binding.tvFollowers.text = "${it.followers} followers"
                binding.tvFollowings.text = "${it.following} following"
                binding.tvMyWork32.text = "15"
                binding.tvMyWork22.text = "0"
            } else {
                Log.d(logTag, "User is null")
            }
        }

        mainViewModel.getUserRepositoriesLiveData.observe(requireActivity()) {
            if (it != null) {
                adapter.submitList(it)
                binding.tvMyWork12.text = it.size.toString()
            } else Log.d(logTag, "Repository list is null")
        }

        mainViewModel.messageLiveData.observe(requireActivity()) {
            try {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        mainViewModel.errorLiveData.observe(requireActivity()) {
            it.printStackTrace()
        }
    }

}