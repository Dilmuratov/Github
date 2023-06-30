package com.example.github.ui.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import com.example.github.databinding.FragmentMenuProfileBinding
import com.example.github.presentation.MainViewModel
import com.example.github.ui.MainActivity
import com.example.github.ui.adapters.RepositoryAdapter
import com.example.github.utils.getStringFromPref
import com.example.github.utils.logTag
import com.example.github.utils.set
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileMenuFragment : Fragment(R.layout.fragment_menu_profile) {
    private lateinit var binding: FragmentMenuProfileBinding
    private val mainViewModel: MainViewModel by viewModel()
    private var adapter = RepositoryAdapter()
    private var login = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuProfileBinding.bind(view)
        binding.recyclerView.adapter = adapter

        initObservers()

        initListeners()

        updateAll()

        getData()

    }

    private fun updateAll() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            MainScope().launch {
                mainViewModel.getUserProfileInfo()
                mainViewModel.getUserRepositories()
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initListeners() {
        binding.llRepository.setOnClickListener {
            findNavController().navigate(R.id.action_profileMenuFragment_to_repositoriesFragment)
        }

        binding.tvFollowers.setOnClickListener {
            if (login.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("login", login)
                bundle.putBoolean("isFollowers", true)
                findNavController().navigate(
                    R.id.action_profileMenuFragment_to_fragmentFollowers,
                    bundle
                )
            }
        }

        binding.tvFollowings.setOnClickListener {
            if (login.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("login", login)
                bundle.putBoolean("isFollowers", false)
                findNavController().navigate(
                    R.id.action_profileMenuFragment_to_fragmentFollowers,
                    bundle
                )
            }
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getData() {
        MainScope().launch {
            binding.swipeRefreshLayout.isRefreshing = true
            mainViewModel.getUserProfileInfo()
            mainViewModel.getUserRepositories()
            Log.d(logTag, "Access key: ${getStringFromPref("access_key")}")
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillWithData(item: UserProfileInfoResponseData) {
        login = item.login
        binding.tvFullName.text = item.name
        binding.tvUsername.text = item.login
        binding.tvLocation.text = item.location
        binding.tvFollowers.text = "${item.followers} followers"
        binding.tvFollowings.text = "${item.following} following"
        binding.tvMyWork32.text = "15"
        binding.tvMyWork22.text = "0"
        binding.ivUserProfile.set(item.avatar_url)
    }

    private fun initObservers() {
        mainViewModel.getUserProfileLiveData.observe(requireActivity()) {
            if (it != null) {
                fillWithData(it)
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

        (requireActivity() as MainActivity).showBottomNavigationView()
    }
}